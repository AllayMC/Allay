package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Pale oak tree feature implementation.
 * Generates large 2x2 pale oak trees found in the pale garden biome.
 *
 * @author daoge_cmd
 */
public class PaleOakTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:pale_oak_tree");

    public PaleOakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.PALE_OAK_LOG,
                BlockTypes.PALE_OAK_LEAVES,
                BlockTypes.PALE_OAK_SAPLING,
                6, 7
        );
    }

    @Override
    protected boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        // Check ground for 2x2 area
        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                if (!canGrowOn(context.getBlockState(x + dx, y - 1, z + dz))) {
                    return false;
                }
            }
        }

        // Check trunk space for 2x2
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx <= 1; dx++) {
                for (int dz = 0; dz <= 1; dz++) {
                    if (!canPlaceLog(context, x + dx, y + dy, z + dz)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight();

        // Check if tree can generate
        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        // Place dirt under the tree (2x2)
        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                placeDirtUnder(context, x + dx, y - 1, z + dz);
            }
        }

        // Place 2x2 trunk
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx <= 1; dx++) {
                for (int dz = 0; dz <= 1; dz++) {
                    placeLog(context, x + dx, y + dy, z + dz);
                }
            }
        }

        // Place wide canopy (similar to dark oak)
        int canopyY = y + height - 1;

        // Bottom layer (-1)
        for (int dx = -2; dx <= 0; dx++) {
            for (int dz = -2; dz <= 0; dz++) {
                placeLeafWithMoss(context, x + dx, canopyY - 1, z + dz, random);
                placeLeafWithMoss(context, 1 + x - dx, canopyY - 1, z + dz, random);
                placeLeafWithMoss(context, x + dx, canopyY - 1, 1 + z - dz, random);
                placeLeafWithMoss(context, 1 + x - dx, canopyY - 1, 1 + z - dz, random);

                if ((dx > -2 || dz > -1) && (dx != -1 || dz != -2)) {
                    placeLeafWithMoss(context, x + dx, canopyY + 1, z + dz, random);
                    placeLeafWithMoss(context, 1 + x - dx, canopyY + 1, z + dz, random);
                    placeLeafWithMoss(context, x + dx, canopyY + 1, 1 + z - dz, random);
                    placeLeafWithMoss(context, 1 + x - dx, canopyY + 1, 1 + z - dz, random);
                }
            }
        }

        // Top leaves (optional)
        if (random.nextBoolean()) {
            placeLeafWithMoss(context, x, canopyY + 2, z, random);
            placeLeafWithMoss(context, x + 1, canopyY + 2, z, random);
            placeLeafWithMoss(context, x + 1, canopyY + 2, z + 1, random);
            placeLeafWithMoss(context, x, canopyY + 2, z + 1, random);
        }

        // Main canopy layer
        for (int dx = -3; dx <= 4; dx++) {
            for (int dz = -3; dz <= 4; dz++) {
                if ((dx != -3 || dz != -3) && (dx != -3 || dz != 4) &&
                    (dx != 4 || dz != -3) && (dx != 4 || dz != 4) &&
                    (Math.abs(dx) < 3 || Math.abs(dz) < 3)) {
                    placeLeafWithMoss(context, x + dx, canopyY, z + dz, random);
                }
            }
        }

        // Add branches
        for (int dx = -1; dx <= 2; dx++) {
            for (int dz = -1; dz <= 2; dz++) {
                if ((dx < 0 || dx > 1 || dz < 0 || dz > 1) && random.nextInt(3) <= 0) {
                    int branchLen = random.nextInt(3) + 2;

                    for (int i = 0; i < branchLen; i++) {
                        placeLog(context, x + dx, canopyY - i - 1, z + dz);
                    }

                    // Leaves around branch
                    for (int bx = -1; bx <= 1; bx++) {
                        for (int bz = -1; bz <= 1; bz++) {
                            placeLeafWithMoss(context, x + dx + bx, canopyY, z + dz + bz, random);
                        }
                    }

                    for (int bx = -2; bx <= 2; bx++) {
                        for (int bz = -2; bz <= 2; bz++) {
                            if (Math.abs(bx) != 2 || Math.abs(bz) != 2) {
                                placeLeafWithMoss(context, x + dx + bx, canopyY - 1, z + dz + bz, random);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Place a leaf block with potential hanging pale moss.
     */
    protected void placeLeafWithMoss(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        if (!canPlaceLeaves(context, x, y, z)) {
            return;
        }

        context.setBlockState(x, y, z, leavesType.getDefaultState());

        // 50% chance to place hanging moss
        if (random.nextBoolean()) {
            int mossDepth = random.nextInt(5) + 1;
            for (int i = 1; i < mossDepth; i++) {
                if (context.getBlockState(x, y - i, z).getBlockType() == BlockTypes.AIR) {
                    context.setBlockState(x, y - i, z, BlockTypes.PALE_HANGING_MOSS.getDefaultState());
                } else {
                    break;
                }
            }
        }
    }
}
