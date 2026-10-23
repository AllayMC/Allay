package org.allaymc.server.entity.ai.route.finder;

import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.ai.route.posevaluator.SpacePosEvaluator;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SpaceAStarRouteFinder} adapts the bounded A* search to twenty-six
 * neighboring cells for flying or swimming entities. Candidate positions use
 * cell centers, reject any block reporting collision, and then delegate
 * suitability to the configured space evaluator. The subclass retains the base
 * search's partial-path and active-entity smoothing behavior.
 *
 * <p>
 * Three-dimensional diagonal distance orders the frontier, while arrival uses a
 * squared spatial tolerance. Smoothing samples rounded cells along the largest
 * coordinate difference and checks intermediate passability without ground
 * support or orthogonal corner checks. As a result, its shortcut rules differ
 * from the ground finder's support-aware vertical transitions. The inherited
 * search wrapper still owns instance cache lifetime, so callers serialize
 * searches even though space checks do not use that cache.
 *
 * @author daoge_cmd
 */
public class SpaceAStarRouteFinder extends FlatAStarRouteFinder {

    /**
     * {@code SQRT3_MINUS_SQRT2} supplies the additional cost of a three-axis
     * diagonal beyond a two-axis diagonal when computing the spatial search
     * heuristic.
     */
    protected static final double SQRT3_MINUS_SQRT2 = Math.sqrt(3) - Math.sqrt(2);

    /**
     * {@code SPACE_NEIGHBORS} enumerates the eight same-level and eighteen
     * vertical neighboring offsets used to expand every adjacent cell around a
     * spatial node.
     */
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

    /**
     * Spatial candidate suitability evaluator.
     */
    protected final SpacePosEvaluator spacePosEvaluator;

    /**
     * Creates a space finder with one hundred expansions and the inherited fall
     * setting of three. The space arrival and barrier overrides use spatial
     * checks instead of ground transition limits.
     */
    public SpaceAStarRouteFinder(SpacePosEvaluator spacePosEvaluator) {
        this(spacePosEvaluator, 100, 3);
    }

    /**
     * Creates a space finder retaining the evaluator and inherited search
     * limits. {@code maxFallDistance} is passed to the base constructor but
     * does not constrain the overridden space neighbors, arrival check, or
     * barriers.
     */
    public SpaceAStarRouteFinder(SpacePosEvaluator spacePosEvaluator, int maxExpandedNodes, int maxFallDistance) {
        super(maxExpandedNodes, maxFallDistance);
        this.spacePosEvaluator = spacePosEvaluator;
    }

    /**
     * Returns passable centered nodes for all twenty-six adjacent cells. Each
     * candidate is checked independently, so diagonal transitions do not
     * additionally require neighboring orthogonal cells to be passable for the
     * entity.
     */
    @Override
    protected List<Node> getNeighbors(Node current, Dimension dimension, EntityIntelligent entity) {
        var neighbors = new ArrayList<Node>();
        // Expansion uses the containing block cell before centering each adjacent destination.
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

    /**
     * Returns whether the cell has no collision and its center passes the space
     * evaluator. Missing block states and any collision data reject the cell
     * before the evaluator is invoked.
     */
    protected boolean isSpacePassable(int x, int y, int z, Dimension dimension, EntityIntelligent entity) {
        var blockState = dimension.getBlockState(x, y, z);
        if (blockState == null) return false;

        if (blockState.getBlockStateData().hasCollision()) return false;

        return spacePosEvaluator.evaluate(entity, new Vector3d(x + 0.5, y + 0.5, z + 0.5));
    }

    /**
     * Returns whether squared spatial separation is below the arrival tolerance
     * of 1.5. This check ignores the inherited fall distance and includes
     * differences along all three coordinate axes.
     */
    @Override
    protected boolean isCloseEnough(Node a, Node b) {
        // Spatial arrival tolerates nearby cell centers without a separate vertical limit.
        return a.getVector().distanceSquared(b.getVector()) < 1.5;
    }

    /**
     * Tests rounded intermediate cells along a three-dimensional shortcut. The
     * number of samples follows the largest coordinate difference, and both
     * endpoint cells are excluded because search already accepted those
     * waypoints.
     *
     * <p>
     * Each sample rejects missing or colliding cells and delegates the
     * remaining space check to the configured evaluator. Rounding approximates
     * the line with block cells rather than sweeping the entity's bounding
     * volume, so the result does not establish full-volume collision clearance
     * or enforce the ground finder's orthogonal corner constraints.
     */
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

        // Round samples at major-axis intervals to approximate intermediate block cells;
        // endpoints are already accepted, and this check does not sweep the entity's full volume.
        for (int i = 1; i < steps; i++) {
            double t = (double) i / steps;
            int x = x1 + (int) Math.round(t * (x2 - x1));
            int y = y1 + (int) Math.round(t * (y2 - y1));
            int z = z1 + (int) Math.round(t * (z2 - z1));

            if (!isSpacePassable(x, y, z, dimension, entity)) return true;
        }
        return false;
    }

    /**
     * Returns three-dimensional diagonal distance for prioritizing
     * twenty-six-direction space expansion.
     */
    @Override
    protected double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getVector().x() - b.getVector().x());
        double dy = Math.abs(a.getVector().y() - b.getVector().y());
        double dz = Math.abs(a.getVector().z() - b.getVector().z());
        // Sort axis differences so three-axis, two-axis, and cardinal travel costs combine correctly.
        double max = Math.max(dx, Math.max(dy, dz));
        double min = Math.min(dx, Math.min(dy, dz));
        double mid = dx + dy + dz - max - min;
        return SQRT3_MINUS_SQRT2 * min + SQRT2_MINUS_1 * mid + max;
    }
}
