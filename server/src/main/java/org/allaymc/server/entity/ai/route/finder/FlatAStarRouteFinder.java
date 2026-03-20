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
 * 2D A* pathfinder for ground-walking entities.
 *
 * @author daoge_cmd
 */
public class FlatAStarRouteFinder implements RouteFinder {

    protected static final double SQRT2_MINUS_1 = Math.sqrt(2) - 1;

    protected static final int[][] FLAT_NEIGHBORS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    protected final GroundPosEvaluator groundPosEvaluator;
    protected final int maxExpandedNodes;
    protected final int maxFallDistance;

    protected HashMap<Long, Boolean> walkableCache;

    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator) {
        this(groundPosEvaluator, 100, 3);
    }

    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator, int maxExpandedNodes, int maxFallDistance) {
        this.groundPosEvaluator = groundPosEvaluator;
        this.maxExpandedNodes = maxExpandedNodes;
        this.maxFallDistance = maxFallDistance;
    }

    protected FlatAStarRouteFinder(int maxExpandedNodes, int maxFallDistance) {
        this.groundPosEvaluator = null;
        this.maxExpandedNodes = maxExpandedNodes;
        this.maxFallDistance = maxFallDistance;
    }

    @Override
    public List<Node> search(EntityIntelligent entity, Vector3dc target) {
        walkableCache = new HashMap<>();
        try {
            return doSearch(entity, target);
        } finally {
            walkableCache = null;
        }
    }

    protected List<Node> doSearch(EntityIntelligent entity, Vector3dc target) {
        var dimension = entity.getDimension();
        var startPos = entity.getLocation();

        int startX = (int) Math.floor(startPos.x());
        int startZ = (int) Math.floor(startPos.z());

        int endX = (int) Math.floor(target.x());
        int endZ = (int) Math.floor(target.z());

        // Use actual Y positions (not integer-floored) so that nodes on partial-height
        // blocks (e.g. slabs) carry the correct standing height for the walk controller.
        var startNode = new Node(startX + 0.5, startPos.y(), startZ + 0.5);
        var endNode = new Node(endX + 0.5, target.y(), endZ + 0.5);

        startNode.setG(0);
        startNode.setH(heuristic(startNode, endNode));

        var openSet = new PriorityQueue<Node>();
        var openMap = new HashMap<Node, Node>();
        var closedSet = new HashSet<Node>();
        openSet.add(startNode);
        openMap.put(startNode, startNode);

        // Track the closest reachable node to the target so that when
        // the exact destination is unreachable, we can still return a
        // partial path toward it instead of giving up entirely.
        Node bestNode = startNode;

        int depth = 0;
        while (!openSet.isEmpty() && depth < maxExpandedNodes) {
            var current = openSet.poll();
            if (closedSet.contains(current)) continue;
            // Skip stale heap entries superseded by a newer node at the same position
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
                    // Create a new node so the stale entry's compareTo stays consistent in the heap
                    var updated = new Node(existing.getVector());
                    updated.setParent(current);
                    updated.setG(tentativeG);
                    updated.setH(existing.getH());
                    openSet.add(updated);
                    openMap.put(updated, updated);
                }
            }
        }
        // Exact path not found — move to the closest reachable node instead
        if (bestNode != startNode) {
            var path = reconstructPath(bestNode);
            return entity.isActive() ? floydSmooth(path, dimension, entity) : path;
        }
        return List.of();
    }

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

            // For diagonal moves, require at least one adjacent cardinal
            // direction to be walkable to prevent corner-cutting through walls
            if (dx != 0 && dz != 0) {
                boolean cardinalXPassable = hasWalkableAt(cx + dx, cy, cz, dimension, entity);
                boolean cardinalZPassable = hasWalkableAt(cx, cy, cz + dz, dimension, entity);
                if (!cardinalXPassable && !cardinalZPassable) continue;
            }

            // Try same level, up 1, or down up to maxFallDistance
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

    protected boolean hasWalkableAt(int x, int cy, int z, Dimension dimension, EntityIntelligent entity) {
        for (int dy = 1; dy >= -maxFallDistance; dy--) {
            if (isWalkable(x, cy + dy, z, dimension, entity)) return true;
        }
        return false;
    }

    protected static long packPos(int x, int y, int z) {
        return (((long) x & 0x3FFFFFFL) << 38) | (((long) y & 0xFFFL) << 26) | ((long) z & 0x3FFFFFFL);
    }

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
     * Compute the actual Y position an entity would stand at for the walkable
     * position (x, y, z). Uses the ground block's collision shape top rather
     * than the integer block Y, so nodes on partial-height blocks (e.g. slabs)
     * carry the correct standing height.
     */
    protected double computeStandingY(int x, int y, int z, Dimension dimension) {
        var groundBlock = dimension.getBlockState(x, y - 1, z);
        if (groundBlock != null) {
            var data = groundBlock.getBlockStateData();
            if (data.hasCollision()) {
                return (y - 1) + data.collisionShape().unionAABB().maxY();
            }
        }
        return y;
    }

    protected boolean isCloseEnough(Node a, Node b) {
        double dx = a.getVector().x() - b.getVector().x();
        double dy = a.getVector().y() - b.getVector().y();
        double dz = a.getVector().z() - b.getVector().z();
        return dx * dx + dz * dz < 1.0 && Math.abs(dy) <= maxFallDistance;
    }

    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        // Octile distance: admissible and consistent for 8-directional movement
        return Math.max(dx, dz) + SQRT2_MINUS_1 * Math.min(dx, dz);
    }

    protected double distance(Node a, Node b) {
        return a.getVector().distance(b.getVector());
    }

    protected List<Node> floydSmooth(List<Node> path, Dimension dimension, EntityIntelligent entity) {
        if (path.size() <= 2) return path;

        // Clear first node's parent so reconstruction stops here
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
        // Connect the last node directly to the final pivot
        path.getLast().setParent(path.get(current));

        // Reconstruct smoothed path by following parent links from end
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
     * Check if there is an unwalkable block between two nodes using Bresenham's line algorithm.
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
        // Use ceil to recover the "walkable Y level" from non-integer standing Y.
        // For a slab at block y=64 (standingY=64.5), ceil gives 65 → isWalkable(x,65,z)
        // checks ground at y=64 (the slab). floor would give 64 → wrong ground block.
        int prevY = (int) Math.ceil(a.getVector().y());

        while (x != x2 || z != z2) {
            int e2 = 2 * err;
            boolean stepX = e2 > -dz;
            boolean stepZ = e2 < dx;
            if (stepX) { err -= dz; x += sx; }
            if (stepZ) { err += dx; z += sz; }

            // Don't check the endpoint (already validated by A*)
            if (x == x2 && z == z2) break;

            // Interpolate Y along the line
            double t = dx >= dz
                    ? (double) (x - x1) / (x2 - x1)
                    : (double) (z - z1) / (z2 - z1);
            int y = (int) Math.ceil(a.getVector().y() + t * (b.getVector().y() - a.getVector().y()));

            if (!isWalkable(x, y, z, dimension, entity)) return true;

            // For diagonal steps, also check both orthogonal cells to prevent corner-cutting
            if (stepX && stepZ) {
                if (!isWalkable(x - sx, y, z, dimension, entity)
                        && !isWalkable(x, y, z - sz, dimension, entity)) {
                    return true;
                }
            }

            // Verify vertical constraints: max +1 climb, max -maxFallDistance drop
            if (y - prevY > 1 || prevY - y > maxFallDistance) return true;
            prevY = y;
        }

        // Also check vertical constraints to the endpoint
        int endY = (int) Math.ceil(b.getVector().y());
        return endY - prevY > 1 || prevY - endY > maxFallDistance;
    }

    protected List<Node> reconstructPath(Node endNode) {
        var path = new ArrayDeque<Node>();
        var current = endNode;
        while (current != null) {
            path.addFirst(current);
            current = current.getParent();
        }
        // Skip the start node — the entity is already there.
        // Without this, the entity would turn back to reach the
        // center of its current block before moving forward.
        if (path.size() > 1) {
            path.removeFirst();
        }
        return new ArrayList<>(path);
    }
}
