package org.allaymc.server.entity.ai.route.finder;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.PosEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.ai.route.SimpleRouteFinder;
import org.joml.Vector3dc;

import java.util.*;

/**
 * 2D A* pathfinder for ground-walking entities.
 *
 * @author daoge_cmd
 */
public class SimpleFlatAStarRouteFinder extends SimpleRouteFinder {

    protected static final int[][] FLAT_NEIGHBORS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    protected final PosEvaluator posEvaluator;
    protected final int maxSearchDepth;
    protected final int maxFallDistance;

    public SimpleFlatAStarRouteFinder(PosEvaluator posEvaluator) {
        this(posEvaluator, 100, 3);
    }

    public SimpleFlatAStarRouteFinder(PosEvaluator posEvaluator, int maxSearchDepth, int maxFallDistance) {
        this.posEvaluator = posEvaluator;
        this.maxSearchDepth = maxSearchDepth;
        this.maxFallDistance = maxFallDistance;
    }

    @Override
    public void search(EntityIntelligent entity, Vector3dc target) {
        super.search(entity, target);
        findPath(entity, target);
    }

    @Override
    public boolean isSearching() {
        return false; // Synchronous search, never "in progress" after search() returns
    }

    @Override
    public boolean isFinished() {
        return true; // Synchronous search is always finished immediately
    }

    protected void findPath(EntityIntelligent entity, Vector3dc target) {
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
                reconstructPath(current);
                return;
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
        // No path found, leave route empty
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
        // The block at feet level and head level must be passable
        var feetBlock = dimension.getBlockState(x, y, z);
        var headBlock = dimension.getBlockState(x, y + 1, z);
        // The block below must be solid (standing surface)
        var groundBlock = dimension.getBlockState(x, y - 1, z);

        if (feetBlock == null || headBlock == null || groundBlock == null) return false;

        boolean feetPassable = !feetBlock.getBlockStateData().hasCollision();
        boolean headPassable = !headBlock.getBlockStateData().hasCollision();
        boolean groundSolid = groundBlock.getBlockStateData().hasCollision();

        if (!feetPassable || !headPassable || !groundSolid) return false;

        // Check with PosEvaluator
        return posEvaluator.evalStandingBlock(entity,
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

    protected void reconstructPath(Node endNode) {
        var path = new ArrayList<Node>();
        var current = endNode;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        route.addAll(path);
    }
}
