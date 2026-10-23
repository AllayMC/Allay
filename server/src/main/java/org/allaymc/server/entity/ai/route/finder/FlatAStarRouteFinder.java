package org.allaymc.server.entity.ai.route.finder;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.ai.route.posevaluator.GroundPosEvaluator;
import org.joml.Vector3dc;

import java.util.*;

/**
 * {@code FlatAStarRouteFinder} searches eight horizontal directions for ground
 * routes with limited climbing and falling. Search nodes preserve actual
 * standing heights, including collision-shape tops on partial blocks, while
 * their XZ coordinates use block centers. A per-search walkability cache avoids
 * repeated evaluation of the same packed block position.
 *
 * <p>
 * The frontier keeps immutable heap costs by replacing improved nodes rather
 * than mutating queued entries. A position map identifies the current entry so
 * stale queue entries can be skipped. The expansion bound can produce a partial
 * route to the expanded node with the smallest heuristic; active entities
 * additionally receive line-of-travel smoothing. The cache is an instance field
 * initialized and cleared around each search, so callers must not overlap
 * searches on the same finder.
 *
 * @author daoge_cmd
 */
public class FlatAStarRouteFinder implements RouteFinder {

    /**
     * {@code SQRT2_MINUS_1} supplies the diagonal correction for the octile
     * heuristic, representing the extra horizontal cost beyond a cardinal step.
     */
    protected static final double SQRT2_MINUS_1 = Math.sqrt(2) - 1;

    /**
     * {@code FLAT_NEIGHBORS} enumerates cardinal and diagonal XZ offsets used
     * for ground expansion; each offset is combined with the allowed vertical
     * search range.
     */
    protected static final int[][] FLAT_NEIGHBORS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    /**
     * Ground support suitability evaluator.
     */
    protected final GroundPosEvaluator groundPosEvaluator;
    /**
     * Maximum nodes expanded per search.
     */
    protected final int maxExpandedNodes;
    /**
     * Maximum downward block-level change.
     */
    protected final int maxFallDistance;

    /**
     * {@code walkableCache} holds results only during {@code search}. The
     * instance field is cleared in {@code finally}, so concurrent searches on
     * this finder can interfere with each other's cache lifetime.
     */
    protected HashMap<Long, Boolean> walkableCache;

    /**
     * Creates a ground finder allowing one hundred expansions and three-block
     * downward transitions. The evaluator is retained directly, and callers
     * must serialize searches because cache state belongs to the finder
     * instance.
     */
    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator) {
        this(groundPosEvaluator, 100, 3);
    }

    /**
     * Creates a ground finder with the supplied evaluator and search limits.
     * The constructor does not validate them; nonpositive expansion limits
     * return an empty route without expanding the starting node.
     */
    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator, int maxExpandedNodes, int maxFallDistance) {
        this.groundPosEvaluator = groundPosEvaluator;
        this.maxExpandedNodes = maxExpandedNodes;
        this.maxFallDistance = maxFallDistance;
    }

    /**
     * Creates base search limits without a ground evaluator for specialized
     * subclasses. Subclasses must replace traversal checks that would otherwise
     * require {@code groundPosEvaluator}, as the space finder does through
     * neighbor generation and barriers.
     */
    protected FlatAStarRouteFinder(int maxExpandedNodes, int maxFallDistance) {
        this.groundPosEvaluator = null;
        this.maxExpandedNodes = maxExpandedNodes;
        this.maxFallDistance = maxFallDistance;
    }

    /**
     * Runs a search with a fresh walkability cache and releases that cache
     * afterward. Callers must serialize searches on this finder; failures
     * propagate after {@code finally} clears the shared cache reference.
     */
    @Override
    public List<Node> search(EntityIntelligent entity, Vector3dc target) {
        // The cache covers only one search so later searches re-evaluate changed blocks.
        // Keep finder instances exclusive to that search until finally clears the reference.
        walkableCache = new HashMap<>();
        try {
            return doSearch(entity, target);
        } finally {
            walkableCache = null;
        }
    }

    /**
     * Searches toward the target until arrival, frontier exhaustion, or the
     * expansion bound. Ground endpoints keep their supplied standing heights,
     * while horizontal positions use block centers. Improved costs publish
     * replacement nodes into both the priority queue and position map so
     * existing heap entries retain their original ordering values.
     *
     * <p>
     * The map rejects superseded entries when they leave the heap. If exact
     * arrival is unavailable, the expanded node with the smallest heuristic
     * supplies a partial path, except when only the start remains best. Active
     * entities pass successful or partial paths through smoothing; inactive
     * entities retain the reconstructed waypoint sequence.
     */
    protected List<Node> doSearch(EntityIntelligent entity, Vector3dc target) {
        var dimension = entity.getDimension();
        var startPos = entity.getLocation();

        int startX = (int) Math.floor(startPos.x());
        int startZ = (int) Math.floor(startPos.z());

        int endX = (int) Math.floor(target.x());
        int endZ = (int) Math.floor(target.z());

        // Preserve standing Y while centering XZ so partial-height support blocks
        // remain distinguishable from integer block levels when controllers follow these nodes.
        var startNode = new Node(startX + 0.5, startPos.y(), startZ + 0.5);
        var endNode = new Node(endX + 0.5, target.y(), endZ + 0.5);

        startNode.setG(0);
        startNode.setH(heuristic(startNode, endNode));

        var openSet = new PriorityQueue<Node>();
        var openMap = new HashMap<Node, Node>();
        var closedSet = new HashSet<Node>();
        openSet.add(startNode);
        openMap.put(startNode, startNode);

        // Retain the expanded node with the smallest heuristic. A bounded or blocked
        // search can then return useful progress even when the destination is never reached.
        Node bestNode = startNode;

        int depth = 0;
        while (!openSet.isEmpty() && depth < maxExpandedNodes) {
            var current = openSet.poll();
            if (closedSet.contains(current)) continue;
            // Cost improvements enqueue replacements without removing older heap entries. The
            // map identifies the current node so a stale entry cannot expand that position again.
            if (openMap.get(current) != current) continue;
            openMap.remove(current);
            if (isCloseEnough(current, endNode)) {
                var path = reconstructPath(current);
                return entity.isActive() ? floydSmooth(path, dimension, entity) : path;
            }

            closedSet.add(current);
            depth++;

            if (current.getH() < bestNode.getH()) {
                bestNode = current;
            }

            for (var neighbor : getNeighbors(current, dimension, entity)) {
                if (closedSet.contains(neighbor)) continue;

                double tentativeG = current.getG() + distance(current, neighbor);

                var existing = openMap.get(neighbor);
                if (existing == null) {
                    neighbor.setParent(current);
                    neighbor.setG(tentativeG);
                    neighbor.setH(heuristic(neighbor, endNode));
                    openSet.add(neighbor);
                    openMap.put(neighbor, neighbor);
                } else if (tentativeG < existing.getG()) {
                    // Changing an enqueued node's cost would leave its heap position stale.
                    // Enqueue a replacement and let the map reject the old entry when polled.
                    var updated = new Node(existing.getVector());
                    updated.setParent(current);
                    updated.setG(tentativeG);
                    updated.setH(existing.getH());
                    openSet.add(updated);
                    openMap.put(updated, updated);
                }
            }
        }
        // Frontier exhaustion or the expansion limit leaves any best expanded node
        // available as a partial destination; the start alone provides no forward route.
        if (bestNode != startNode) {
            var path = reconstructPath(bestNode);
            return entity.isActive() ? floydSmooth(path, dimension, entity) : path;
        }
        return List.of();
    }

    /**
     * Builds neighboring ground nodes from the eight horizontal offsets and
     * permitted height range. Diagonal expansion requires at least one adjacent
     * cardinal column to contain a walkable position, limiting passage through
     * a fully blocked corner.
     *
     * <p>
     * Each target column is tested from one block above downward through {@code
     * maxFallDistance}. The first accepted level wins even when a lower level
     * is also walkable, and its collision-derived standing height becomes the
     * node's Y coordinate. The method therefore combines horizontal corner
     * constraints with vertical support selection before assigning traversal
     * costs.
     */
    protected List<Node> getNeighbors(Node current, Dimension dimension, EntityIntelligent entity) {
        var neighbors = new ArrayList<Node>();
        int cx = (int) Math.floor(current.getVector().x());
        int cy = (int) Math.floor(current.getVector().y());
        int cz = (int) Math.floor(current.getVector().z());

        for (var offset : FLAT_NEIGHBORS) {
            int dx = offset[0];
            int dz = offset[1];
            int nx = cx + dx;
            int nz = cz + dz;

            // Reject a diagonal only when both neighboring cardinal columns are blocked,
            // preventing this expansion from crossing a corner with no passable side.
            if (dx != 0 && dz != 0) {
                boolean cardinalXPassable = hasWalkableAt(cx + dx, cy, cz, dimension, entity);
                boolean cardinalZPassable = hasWalkableAt(cx, cy, cz + dz, dimension, entity);
                if (!cardinalXPassable && !cardinalZPassable) continue;
            }

            // Prefer the highest accepted support level in this column. Starting one
            // level above permits climbing, while the lower bound limits downward transitions.
            for (int dy = 1; dy >= -maxFallDistance; dy--) {
                int ny = cy + dy;
                if (isWalkable(nx, ny, nz, dimension, entity)) {
                    neighbors.add(new Node(nx + 0.5, computeStandingY(nx, ny, nz, dimension), nz + 0.5));
                    break;
                }
            }
        }

        return neighbors;
    }

    /**
     * Returns whether the column has support within the permitted vertical
     * range. Levels are checked from one above the current level down through
     * {@code maxFallDistance}, matching neighbor generation's available
     * transitions.
     */
    protected boolean hasWalkableAt(int x, int cy, int z, Dimension dimension, EntityIntelligent entity) {
        for (int dy = 1; dy >= -maxFallDistance; dy--) {
            if (isWalkable(x, cy + dy, z, dimension, entity)) return true;
        }
        return false;
    }

    /**
     * Packs block coordinates into a cache key with 26-bit XZ and 12-bit Y
     * fields. Coordinates outside those bit widths alias other positions, so
     * callers rely on the search's supported coordinate range.
     */
    protected static long packPos(int x, int y, int z) {
        // Masked coordinate fields keep cached support checks distinct within their bit ranges.
        return (((long) x & 0x3FFFFFFL) << 38) | (((long) y & 0xFFFL) << 26) | ((long) z & 0x3FFFFFFL);
    }

    /**
     * Returns cached support suitability or evaluates the block below the
     * supplied standing level. A missing support block caches {@code false};
     * this helper requires an active search cache and a usable ground
     * evaluator.
     */
    protected boolean isWalkable(int x, int y, int z, Dimension dimension, EntityIntelligent entity) {
        var key = packPos(x, y, z);
        var cached = walkableCache.get(key);
        if (cached != null) return cached;

        var groundBlock = dimension.getBlockState(x, y - 1, z);
        if (groundBlock == null) {
            walkableCache.put(key, false);
            return false;
        }

        boolean result = groundPosEvaluator.evaluate(entity,
                new Block(groundBlock, new Position3i(x, y - 1, z, dimension)));
        walkableCache.put(key, result);
        return result;
    }

    /**
     * Returns the support collision shape's top in world coordinates when
     * available. Missing support or absent collision data falls back to the
     * supplied integer standing level, preserving a usable waypoint height for
     * accepted custom evaluations.
     */
    protected double computeStandingY(int x, int y, int z, Dimension dimension) {
        var groundBlock = dimension.getBlockState(x, y - 1, z);
        if (groundBlock != null) {
            var data = groundBlock.getBlockStateData();
            if (data.hasCollision()) {
                return (y - 1) + data.collisionShape().unionAABB().maxY();
            }
        }
        // Custom ground evaluators can accept support without collision data. Keep the
        // integer standing level when no collision top is available for a more precise height.
        return y;
    }

    /**
     * Returns whether horizontal separation is below one block and vertical
     * separation fits the fall limit. This ground arrival tolerance can accept
     * a target at a different standing height within that limit.
     */
    protected boolean isCloseEnough(Node a, Node b) {
        double dx = a.getVector().x() - b.getVector().x();
        double dy = a.getVector().y() - b.getVector().y();
        double dz = a.getVector().z() - b.getVector().z();
        // Horizontal block-center tolerance combines with the configured vertical transition limit.
        return dx * dx + dz * dz < 1.0 && Math.abs(dy) <= maxFallDistance;
    }

    /**
     * Returns octile horizontal distance used to prioritize eight-direction
     * ground expansion.
     */
    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        // Diagonal horizontal steps cost square-root two rather than two cardinal steps.
        return Math.max(dx, dz) + SQRT2_MINUS_1 * Math.min(dx, dz);
    }

    /**
     * Returns Euclidean world distance between the supplied pair of route
     * nodes.
     */
    protected double distance(Node a, Node b) {
        return a.getVector().distance(b.getVector());
    }

    /**
     * Shortens a route by linking visible pivots through the existing node
     * objects. Paths with at most two nodes are returned directly; longer paths
     * have parent links rewritten, so the input's node objects are mutated even
     * though the result list is newly constructed.
     *
     * <p>
     * The first node's parent is cleared to bound reconstruction. Each blocked
     * shortcut retains the preceding node as the next pivot, and the final
     * endpoint links to the final pivot. Following those rewritten parents
     * produces the returned route while preserving the endpoints of the
     * supplied waypoint sequence.
     */
    protected List<Node> floydSmooth(List<Node> path, Dimension dimension, EntityIntelligent entity) {
        if (path.size() <= 2) return path;

        // The reconstructed input omits its original start when it has later nodes.
        // Clear the first retained parent so smoothing cannot reintroduce that omitted position.
        path.getFirst().setParent(null);

        int current = 0;
        int total = 2;
        while (total < path.size()) {
            if (hasBarrier(path.get(current), path.get(total), dimension, entity)) {
                path.get(total - 1).setParent(path.get(current));
                current = total - 1;
            }
            total++;
        }
        path.getLast().setParent(path.get(current));

        var result = new ArrayList<Node>();
        var node = path.getLast();
        result.add(node);
        while (node.getParent() != null) {
            result.add(node = node.getParent());
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Tests a prospective ground shortcut using horizontal Bresenham samples
     * and vertical transition limits. Standing heights are converted with
     * ceiling before support checks, preserving the intended ground block
     * beneath partial-height surfaces. Intermediate positions must be walkable,
     * and diagonal samples require at least one orthogonal cell to remain
     * walkable.
     *
     * <p>
     * The endpoint is already accepted by search and is excluded from
     * intermediate support checks. Its final vertical transition is still
     * tested. Interpolated standing heights permit gradual shortcut height
     * changes, but any climb greater than one level or drop beyond {@code
     * maxFallDistance} rejects the shortcut.
     */
    protected boolean hasBarrier(Node a, Node b, Dimension dimension, EntityIntelligent entity) {
        int x1 = (int) Math.floor(a.getVector().x());
        int z1 = (int) Math.floor(a.getVector().z());
        int x2 = (int) Math.floor(b.getVector().x());
        int z2 = (int) Math.floor(b.getVector().z());

        if (x1 == x2 && z1 == z2) return false;

        int dx = Math.abs(x2 - x1);
        int dz = Math.abs(z2 - z1);
        int sx = Integer.signum(x2 - x1);
        int sz = Integer.signum(z2 - z1);
        int err = dx - dz;
        int x = x1, z = z1;
        // A slab at block Y 64 can produce standing Y 64.5. Ceiling selects
        // standing level 65, whose support check reads block 64; flooring would read block 63.
        int prevY = (int) Math.ceil(a.getVector().y());

        while (x != x2 || z != z2) {
            int e2 = 2 * err;
            boolean stepX = e2 > -dz;
            boolean stepZ = e2 < dx;
            if (stepX) { err -= dz; x += sx; }
            if (stepZ) { err += dx; z += sz; }

            // Search already accepted the endpoint support, so only its final vertical
            // transition is checked below; intermediate support checks apply to shortcut cells.
            if (x == x2 && z == z2) break;

            // Ceiling preserves the supporting block level along interpolated standing heights.
            double t = dx >= dz
                    ? (double) (x - x1) / (x2 - x1)
                    : (double) (z - z1) / (z2 - z1);
            int y = (int) Math.ceil(a.getVector().y() + t * (b.getVector().y() - a.getVector().y()));

            if (!isWalkable(x, y, z, dimension, entity)) return true;

            // Reject a sampled diagonal when both orthogonal alternatives are blocked,
            // matching the corner constraint used while generating the original ground route.
            if (stepX && stepZ) {
                if (!isWalkable(x - sx, y, z, dimension, entity)
                        && !isWalkable(x, y, z - sz, dimension, entity)) {
                    return true;
                }
            }

            // Shortcut samples must retain the climb and drop limits of neighbor generation.
            if (y - prevY > 1 || prevY - y > maxFallDistance) return true;
            prevY = y;
        }

        // Endpoint support was accepted earlier; its transition must still respect height limits.
        int endY = (int) Math.ceil(b.getVector().y());
        return endY - prevY > 1 || prevY - endY > maxFallDistance;
    }

    /**
     * Returns the parent chain in forward order, omitting the start when later
     * nodes exist. The returned list contains the original mutable node objects
     * rather than copies, including their retained parent links.
     */
    protected List<Node> reconstructPath(Node endNode) {
        var path = new ArrayDeque<Node>();
        var current = endNode;
        while (current != null) {
            path.addFirst(current);
            current = current.getParent();
        }
        // The entity already occupies the start block. Omit that node when later
        // nodes exist so walking does not turn back toward the current block center first.
        if (path.size() > 1) {
            path.removeFirst();
        }
        return new ArrayList<>(path);
    }
}
