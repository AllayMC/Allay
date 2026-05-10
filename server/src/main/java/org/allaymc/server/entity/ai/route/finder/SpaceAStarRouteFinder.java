package org.allaymc.server.entity.ai.route.finder;

import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.ai.route.posevaluator.SpacePosEvaluator;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

/**
 * 3D A* pathfinder for flying/swimming entities. Extends the flat A* pathfinder
 * to add vertical movement in all 26 directions.
 *
 * @author daoge_cmd
 */
public class SpaceAStarRouteFinder extends FlatAStarRouteFinder {

    protected static final double SQRT3_MINUS_SQRT2 = Math.sqrt(3) - Math.sqrt(2);

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

    protected final SpacePosEvaluator spacePosEvaluator;

    public SpaceAStarRouteFinder(SpacePosEvaluator spacePosEvaluator) {
        this(spacePosEvaluator, 100, 3);
    }

    public SpaceAStarRouteFinder(SpacePosEvaluator spacePosEvaluator, int maxExpandedNodes, int maxFallDistance) {
        super(maxExpandedNodes, maxFallDistance);
        this.spacePosEvaluator = spacePosEvaluator;
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

        return spacePosEvaluator.evaluate(entity, new Vector3d(x + 0.5, y + 0.5, z + 0.5));
    }

    @Override
    protected boolean isCloseEnough(Node a, Node b) {
        return a.getVector().distanceSquared(b.getVector()) < 1.5;
    }

    @Override
    protected boolean hasBarrier(Node a, Node b, Dimension dimension, EntityIntelligent entity) {
        int x1 = (int) Math.floor(a.getVector().x());
        int y1 = (int) Math.floor(a.getVector().y());
        int z1 = (int) Math.floor(a.getVector().z());
        int x2 = (int) Math.floor(b.getVector().x());
        int y2 = (int) Math.floor(b.getVector().y());
        int z2 = (int) Math.floor(b.getVector().z());

        if (x1 == x2 && y1 == y2 && z1 == z2) return false;

        int steps = Math.max(Math.abs(x2 - x1), Math.max(Math.abs(y2 - y1), Math.abs(z2 - z1)));

        for (int i = 1; i < steps; i++) {
            double t = (double) i / steps;
            int x = x1 + (int) Math.round(t * (x2 - x1));
            int y = y1 + (int) Math.round(t * (y2 - y1));
            int z = z1 + (int) Math.round(t * (z2 - z1));

            if (!isSpacePassable(x, y, z, dimension, entity)) return true;
        }
        return false;
    }

    @Override
    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dy = Math.abs(a.getVector().y() - b.getVector().y());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        // 3D Octile distance: admissible for 26-directional movement
        double max = Math.max(dx, Math.max(dy, dz));
        double min = Math.min(dx, Math.min(dy, dz));
        double mid = dx + dy + dz - max - min;
        return SQRT3_MINUS_SQRT2 * min + SQRT2_MINUS_1 * mid + max;
    }
}
