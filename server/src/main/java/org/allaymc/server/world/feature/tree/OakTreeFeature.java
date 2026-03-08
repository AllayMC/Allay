package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Oak tree feature implementation.
 * Generates standard oak trees with optional bee nest.
 *
 * @author daoge_cmd
 */
public class OakTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:oak_tree");

    public OakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.OAK_LEAVES,
                BlockTypes.OAK_SAPLING,
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

        // Place dirt under the tree
        placeDirtUnder(context, x, y - 1, z);

        // Place trunk
        for (int dy = 0; dy < height; dy++) {
            placeLog(context, x, y + dy, z);
        }

        // Place leaves
        int leafStart = height - 3;
        for (int dy = leafStart; dy <= height; dy++) {
            int radius;
            if (dy == height) {
                radius = 1;
            } else if (dy == leafStart) {
                radius = 1;
            } else {
                radius = 2;
            }

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    // Skip corners on the outer layer
                    if (Math.abs(dx) == radius && Math.abs(dz) == radius) {
                        if (random.nextBoolean()) {
                            continue;
                        }
                    }
                    // Don't replace log blocks
                    if (dx == 0 && dz == 0 && dy < height) {
                        continue;
                    }
                    placeLeaves(context, x + dx, y + dy, z + dz);
                }
            }
        }

        // Top leaves
        placeLeaves(context, x, y + height, z);

        // Try to place bee nest (5% chance for sapling growth)
        if (random.nextFloat() < 0.05f) {
            tryPlaceBeeNest(context, x, y + height - 2, z, random.nextInt(4));
        }

        return true;
    }

    /**
     * Try to place a bee nest on the side of the tree.
     */
    protected void tryPlaceBeeNest(WorldFeatureContext context, int x, int y, int z, int side) {
        int beeX = x;
        int beeZ = z;
        switch (side) {
            case 0 -> beeZ -= 1;
            case 1 -> beeZ += 1;
            case 2 -> beeX -= 1;
            case 3 -> beeX += 1;
        }

        var blockState = context.getBlockState(beeX, y, beeZ);
        if (blockState.getBlockType() == leavesType) {
            context.setBlockState(beeX, y, beeZ, BlockTypes.BEE_NEST.getDefaultState());
        }
    }
}
