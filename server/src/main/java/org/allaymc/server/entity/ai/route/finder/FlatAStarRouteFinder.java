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

    protected static final int[][] FLAT_NEIGHBORS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    protected final GroundPosEvaluator groundPosEvaluator;
    protected final int maxSearchDepth;
    protected final int maxFallDistance;

    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator) {
        this(groundPosEvaluator, 100, 3);
    }

    public FlatAStarRouteFinder(GroundPosEvaluator groundPosEvaluator, int maxSearchDepth, int maxFallDistance) {
        this.groundPosEvaluator = groundPosEvaluator;
        this.maxSearchDepth = maxSearchDepth;
        this.maxFallDistance = maxFallDistance;
    }

    protected FlatAStarRouteFinder(int maxSearchDepth, int maxFallDistance) {
        this.groundPosEvaluator = null;
        this.maxSearchDepth = maxSearchDepth;
        this.maxFallDistance = maxFallDistance;
    }

    @Override
    public List<Node> search(EntityIntelligent entity, Vector3dc target) {
        var dimension = entity.getDimension();
        var startPos = entity.getLocation();

        int startX = (int) Math.floor(startPos.x());
        int startY = (int) Math.floor(startPos.y());
        int startZ = (int) Math.floor(startPos.z());

        int endX = (int) Math.floor(target.x());
        int endY = (int) Math.floor(target.y());
        int endZ = (int) Math.floor(target.z());

        var startNode = new Node(startX + 0.5, startY, startZ + 0.5);
        var endNode = new Node(endX + 0.5, endY, endZ + 0.5);

        startNode.setG(0);
        startNode.setH(heuristic(startNode, endNode));

        var openSet = new PriorityQueue<Node>();
        var closedSet = new HashSet<Node>();
        openSet.add(startNode);

        int depth = 0;
        while (!openSet.isEmpty() && depth < maxSearchDepth) {
            var current = openSet.poll();
            if (isCloseEnough(current, endNode)) {
                return floydSmooth(reconstructPath(current), dimension, entity);
            }

            closedSet.add(current);
            depth++;

            for (var neighbor : getNeighbors(current, dimension, entity)) {
                if (closedSet.contains(neighbor)) continue;

                double tentativeG = current.getG() + distance(current, neighbor);

                boolean inOpen = openSet.contains(neighbor);
                if (!inOpen || tentativeG < neighbor.getG()) {
                    neighbor.setParent(current);
                    neighbor.setG(tentativeG);
                    neighbor.setH(heuristic(neighbor, endNode));
                    if (!inOpen) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        // No path found
        return List.of();
    }

    protected List<Node> getNeighbors(Node current, Dimension dimension, EntityIntelligent entity) {
        var neighbors = new ArrayList<Node>();
        int cx = (int) Math.floor(current.getVector().x());
        int cy = (int) Math.floor(current.getVector().y());
        int cz = (int) Math.floor(current.getVector().z());

        for (var offset : FLAT_NEIGHBORS) {
            int nx = cx + offset[0];
            int nz = cz + offset[1];

            // Try same level, up 1, or down up to maxFallDistance
            for (int dy = 1; dy >= -maxFallDistance; dy--) {
                int ny = cy + dy;
                if (isWalkable(nx, ny, nz, dimension, entity)) {
                    neighbors.add(new Node(nx + 0.5, ny, nz + 0.5));
                    break;
                }
            }
        }

        return neighbors;
    }

    protected boolean isWalkable(int x, int y, int z, Dimension dimension, EntityIntelligent entity) {
        var groundBlock = dimension.getBlockState(x, y - 1, z);
        if (groundBlock == null) return false;

        return groundPosEvaluator.evaluate(entity,
                new Block(groundBlock, new Position3i(x, y - 1, z, dimension)));
    }

    protected boolean isCloseEnough(Node a, Node b) {
        double dx = a.getVector().x() - b.getVector().x();
        double dz = a.getVector().z() - b.getVector().z();
        return dx * dx + dz * dz < 1.0;
    }

    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        return dx + dz;
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
            if (hasBarrier(path.get(current), path.get(total), dimension, entity) || total == path.size() - 1) {
                path.get(total - 1).setParent(path.get(current));
                current = total - 1;
            }
            total++;
        }

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

        while (x != x2 || z != z2) {
            int e2 = 2 * err;
            if (e2 > -dz) { err -= dz; x += sx; }
            if (e2 < dx) { err += dx; z += sz; }

            // Don't check the endpoint (already validated by A*)
            if (x == x2 && z == z2) break;

            // Interpolate Y along the line
            double t = dx >= dz
                    ? (double) (x - x1) / (x2 - x1)
                    : (double) (z - z1) / (z2 - z1);
            int y = (int) Math.floor(a.getVector().y() + t * (b.getVector().y() - a.getVector().y()));

            if (!isWalkable(x, y, z, dimension, entity)) return true;
        }
        return false;
    }

    protected List<Node> reconstructPath(Node endNode) {
        var path = new ArrayList<Node>();
        var current = endNode;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        // Skip the start node — the entity is already there.
        // Without this, the entity would turn back to reach the
        // center of its current block before moving forward.
        if (path.size() > 1) {
            path.removeFirst();
        }
        return path;
    }
}
