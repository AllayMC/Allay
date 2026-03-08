package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Dark oak tree feature implementation.
 * Generates large 2x2 dark oak trees.
 *
 * @author daoge_cmd
 */
public class DarkOakTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:dark_oak_tree");

    public DarkOakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.DARK_OAK_LOG,
                BlockTypes.DARK_OAK_LEAVES,
                BlockTypes.DARK_OAK_SAPLING,
                6, 10
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

        // Place wide canopy
        int canopyStart = height - 4;
        for (int dy = canopyStart; dy <= height + 1; dy++) {
            int radius;
            if (dy >= height) {
                radius = 2;
            } else if (dy == canopyStart) {
                radius = 2;
            } else {
                radius = 3;
            }

            for (int dx = -radius; dx <= radius + 1; dx++) {
                for (int dz = -radius; dz <= radius + 1; dz++) {
                    // Skip corners for natural look
                    int absX = Math.abs(dx - 0.5) > radius ? 1 : 0;
                    int absZ = Math.abs(dz - 0.5) > radius ? 1 : 0;
                    if (absX + absZ > 1) {
                        if (random.nextBoolean()) continue;
                    }

                    // Don't replace log blocks
                    if (dx >= 0 && dx <= 1 && dz >= 0 && dz <= 1 && dy < height) {
                        continue;
                    }

                    placeLeaves(context, x + dx, y + dy, z + dz);
                }
            }
        }

        // Add branches
        if (random.nextBoolean()) {
            addBranch(context, x - 1, y + height - 2, z, -1, 0);
        }
        if (random.nextBoolean()) {
            addBranch(context, x + 2, y + height - 2, z, 1, 0);
        }
        if (random.nextBoolean()) {
            addBranch(context, x, y + height - 2, z - 1, 0, -1);
        }
        if (random.nextBoolean()) {
            addBranch(context, x, y + height - 2, z + 2, 0, 1);
        }

        return true;
    }

    /**
     * Add a branch extending from the trunk.
     */
    protected void addBranch(WorldFeatureContext context, int x, int y, int z, int dirX, int dirZ) {
        // Place branch log
        placeLog(context, x, y, z);
        placeLog(context, x + dirX, y, z + dirZ);

        // Place leaves around branch end
        int bx = x + dirX * 2;
        int bz = z + dirZ * 2;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                placeLeaves(context, bx + dx, y, bz + dz);
                if (Math.abs(dx) != 1 || Math.abs(dz) != 1) {
                    placeLeaves(context, bx + dx, y + 1, bz + dz);
                }
            }
        }
    }
}
