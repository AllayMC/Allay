package org.allaymc.server.entity.ai.route.finder;

import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.PosEvaluator;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

/**
 * 3D A* pathfinder for flying/swimming entities. Extends the flat A* pathfinder
 * to add vertical movement in all 26 directions.
 *
 * @author daoge_cmd
 */
public class SimpleSpaceAStarRouteFinder extends SimpleFlatAStarRouteFinder {

    protected static final int[][] SPACE_NEIGHBORS = {
            // Same level (8 directions)
            {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1},
            {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1},
            // Up (9 directions)
            {0, 1, 0},
            {1, 1, 0}, {-1, 1, 0}, {0, 1, 1}, {0, 1, -1},
            {1, 1, 1}, {1, 1, -1}, {-1, 1, 1}, {-1, 1, -1},
            // Down (9 directions)
            {0, -1, 0},
            {1, -1, 0}, {-1, -1, 0}, {0, -1, 1}, {0, -1, -1},
            {1, -1, 1}, {1, -1, -1}, {-1, -1, 1}, {-1, -1, -1}
    };

    public SimpleSpaceAStarRouteFinder(PosEvaluator posEvaluator) {
        super(posEvaluator);
    }

    public SimpleSpaceAStarRouteFinder(PosEvaluator posEvaluator, int maxSearchDepth, int maxFallDistance) {
        super(posEvaluator, maxSearchDepth, maxFallDistance);
    }

    @Override
    protected List<Node> getNeighbors(Node current, Dimension dimension, EntityIntelligent entity) {
        var neighbors = new ArrayList<Node>();
        int cx = (int) Math.floor(current.getVector().x());
        int cy = (int) Math.floor(current.getVector().y());
        int cz = (int) Math.floor(current.getVector().z());

        for (var offset : SPACE_NEIGHBORS) {
            int nx = cx + offset[0];
            int ny = cy + offset[1];
            int nz = cz + offset[2];

            if (isSpacePassable(nx, ny, nz, dimension, entity)) {
                neighbors.add(new Node(nx + 0.5, ny + 0.5, nz + 0.5));
            }
        }

        return neighbors;
    }

    protected boolean isSpacePassable(int x, int y, int z, Dimension dimension, EntityIntelligent entity) {
        var blockState = dimension.getBlockState(x, y, z);
        if (blockState == null) return false;

        if (blockState.getBlockStateData().hasCollision()) return false;

        return posEvaluator.evalPos(entity, new Vector3d(x + 0.5, y + 0.5, z + 0.5));
    }

    @Override
    protected boolean isCloseEnough(Node a, Node b) {
        return a.getVector().distanceSquared(b.getVector()) < 1.5;
    }

    @Override
    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dy = Math.abs(a.getVector().y() - b.getVector().y());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        return dx + dy + dz;
    }
}
