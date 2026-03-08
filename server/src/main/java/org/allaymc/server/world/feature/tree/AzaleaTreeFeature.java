package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Azalea tree feature implementation.
 * Generates azalea trees with a bending oak log trunk, mixed azalea/flowering azalea leaves,
 * and rooted dirt below the trunk base. Matches vanilla Minecraft tree shape.
 *
 * @author daoge_cmd
 */
public class AzaleaTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:azalea_tree");

    private static final int MIN_HEIGHT_FOR_LEAVES = 3;
    private static final int FOLIAGE_RADIUS = 3;
    private static final int FOLIAGE_HEIGHT = 2;
    private static final int LEAF_PLACEMENT_ATTEMPTS = 50;

    public AzaleaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.AZALEA_LEAVES,
                null, // No sapling type - azalea is not a sapling
                4, 6
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight();

        // Check if tree can generate
        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        // Pick a random horizontal direction for the bend
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // N, S, W, E
        int[] bendDir = directions[random.nextInt(4)];
        int bendDx = bendDir[0];
        int bendDz = bendDir[1];

        // Place rooted dirt under the tree
        context.setBlockState(x, y - 1, z, BlockTypes.DIRT_WITH_ROOTS.getDefaultState());

        // Track foliage attachment points
        List<int[]> foliageAttachments = new ArrayList<>();

        // Place vertical trunk with bending near the top
        int curX = x;
        int curZ = z;
        int topIndex = height - 1;
        for (int i = 0; i <= topIndex; i++) {
            // Bending check: shift horizontally near the top of the trunk
            if (i + 1 >= topIndex + random.nextInt(2)) {
                curX += bendDx;
                curZ += bendDz;
            }

            if (canPlaceLog(context, curX, y + i, curZ)) {
                placeLog(context, curX, y + i, curZ);
            }

            // Add foliage attachment at upper parts of the trunk
            if (i >= MIN_HEIGHT_FOR_LEAVES) {
                foliageAttachments.add(new int[]{curX, y + i, curZ});
            }
        }

        // Place horizontal bend extension (1-2 blocks extending sideways)
        int bendLength = 1 + random.nextInt(2); // 1 or 2
        int bendY = y + height; // Y level above the top of the vertical trunk
        for (int i = 0; i <= bendLength; i++) {
            if (canPlaceLog(context, curX, bendY, curZ)) {
                placeLog(context, curX, bendY, curZ);
            }
            foliageAttachments.add(new int[]{curX, bendY, curZ});
            curX += bendDx;
            curZ += bendDz;
        }

        // Place foliage around each attachment point using random scatter
        for (int[] attachment : foliageAttachments) {
            placeFoliageCluster(context, attachment[0], attachment[1], attachment[2], random);
        }

        return true;
    }

    /**
     * Place a cluster of leaves around a foliage attachment point.
     * Uses random scatter with triangular distribution matching vanilla behavior.
     */
    protected void placeFoliageCluster(WorldFeatureContext context, int cx, int cy, int cz, ThreadLocalRandom random) {
        for (int i = 0; i < LEAF_PLACEMENT_ATTEMPTS; i++) {
            // Triangular distribution offsets (nextInt(3) - nextInt(3) gives range -2 to +2)
            int dx = random.nextInt(FOLIAGE_RADIUS) - random.nextInt(FOLIAGE_RADIUS);
            int dy = random.nextInt(FOLIAGE_HEIGHT) - random.nextInt(FOLIAGE_HEIGHT);
            int dz = random.nextInt(FOLIAGE_RADIUS) - random.nextInt(FOLIAGE_RADIUS);

            int lx = cx + dx;
            int ly = cy + dy;
            int lz = cz + dz;

            if (canPlaceLeaves(context, lx, ly, lz)) {
                placeAzaleaLeaf(context, lx, ly, lz, random);
            }
        }
    }

    /**
     * Place a single azalea leaf block.
     * 75% regular azalea leaves, 25% flowering azalea leaves.
     */
    protected void placeAzaleaLeaf(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        if (random.nextInt(4) == 0) {
            context.setBlockState(x, y, z, BlockTypes.AZALEA_LEAVES_FLOWERED.getDefaultState());
        } else {
            context.setBlockState(x, y, z, BlockTypes.AZALEA_LEAVES.getDefaultState());
        }
    }
}
