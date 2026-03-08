package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Mega jungle tree feature implementation.
 * Generates large 2x2 jungle trees with vines and cocoa.
 *
 * @author daoge_cmd
 */
public class MegaJungleTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:mega_jungle_tree");

    // Vine direction bits:
    // 1 = south face, 2 = west face, 4 = north face, 8 = east face
    protected static final int VINE_SOUTH = 1;
    protected static final int VINE_WEST = 2;
    protected static final int VINE_NORTH = 4;
    protected static final int VINE_EAST = 8;

    public MegaJungleTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.JUNGLE_LOG,
                BlockTypes.JUNGLE_LEAVES,
                BlockTypes.JUNGLE_SAPLING,
                15, 25
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

        // Place canopy
        int canopyStart = height - 4;
        for (int dy = canopyStart; dy <= height + 1; dy++) {
            int radius;
            if (dy >= height) {
                radius = 2;
            } else {
                radius = 3 + random.nextInt(2);
            }

            for (int dx = -radius; dx <= radius + 1; dx++) {
                for (int dz = -radius; dz <= radius + 1; dz++) {
                    float distX = dx - 0.5f;
                    float distZ = dz - 0.5f;
                    float dist = (float) Math.sqrt(distX * distX + distZ * distZ);

                    if (dist <= radius + 0.5f) {
                        if (dx >= 0 && dx <= 1 && dz >= 0 && dz <= 1 && dy < height) {
                            continue;
                        }
                        if (dist > radius - 0.5f && random.nextFloat() < 0.3f) {
                            continue;
                        }
                        placeLeaves(context, x + dx, y + dy, z + dz);
                    }
                }
            }
        }

        // Add branches with leaves
        addBranches(context, x, y, z, height);

        // Add vines
        placeVines(context, x, y, z, height);

        // Add cocoa beans
        if (random.nextFloat() < 0.5f) {
            placeCocoa(context, x, y, z, height);
        }

        return true;
    }

    /**
     * Add branches extending from the trunk.
     */
    protected void addBranches(WorldFeatureContext context, int x, int y, int z, int height) {
        var random = ThreadLocalRandom.current();
        int branchCount = 2 + random.nextInt(3);

        for (int i = 0; i < branchCount; i++) {
            int branchY = y + height / 2 + random.nextInt(height / 2 - 2);
            int dirX = random.nextBoolean() ? -1 : 2;
            int dirZ = random.nextBoolean() ? -1 : 2;

            if (random.nextBoolean()) {
                // X direction branch
                int bx = x + dirX;
                placeLog(context, bx, branchY, z);
                placeLog(context, bx, branchY, z + 1);
                bx += (dirX > 0 ? 1 : -1);
                placeLog(context, bx, branchY + 1, z);

                // Leaves at branch end
                placeBranchLeaves(context, bx, branchY + 1, z);
            } else {
                // Z direction branch
                int bz = z + dirZ;
                placeLog(context, x, branchY, bz);
                placeLog(context, x + 1, branchY, bz);
                bz += (dirZ > 0 ? 1 : -1);
                placeLog(context, x, branchY + 1, bz);

                // Leaves at branch end
                placeBranchLeaves(context, x, branchY + 1, bz);
            }
        }
    }

    /**
     * Place leaves around a branch end.
     */
    protected void placeBranchLeaves(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (Math.abs(dx) == 2 && Math.abs(dz) == 2) continue;
                    if (Math.abs(dy) == 1 && (Math.abs(dx) == 2 || Math.abs(dz) == 2)) continue;
                    if (random.nextFloat() < 0.2f) continue;
                    placeLeaves(context, x + dx, y + dy, z + dz);
                }
            }
        }
    }

    /**
     * Place vines on the trunk sides.
     */
    protected void placeVines(WorldFeatureContext context, int x, int y, int z, int height) {
        var random = ThreadLocalRandom.current();

        // Vines on trunk sides with proper direction bits
        // West side of trunk (x-1)
        if (random.nextFloat() < 0.7f) {
            int vineLen = height / 2 + random.nextInt(height / 2);
            for (int dy = 0; dy < vineLen; dy++) {
                if (context.getBlockState(x - 1, y + dy, z).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x - 1, y + dy, z, VINE_EAST);
                }
                if (context.getBlockState(x - 1, y + dy, z + 1).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x - 1, y + dy, z + 1, VINE_EAST);
                }
            }
        }

        // East side of trunk (x+2)
        if (random.nextFloat() < 0.7f) {
            int vineLen = height / 2 + random.nextInt(height / 2);
            for (int dy = 0; dy < vineLen; dy++) {
                if (context.getBlockState(x + 2, y + dy, z).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x + 2, y + dy, z, VINE_WEST);
                }
                if (context.getBlockState(x + 2, y + dy, z + 1).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x + 2, y + dy, z + 1, VINE_WEST);
                }
            }
        }

        // North side of trunk (z-1)
        if (random.nextFloat() < 0.7f) {
            int vineLen = height / 2 + random.nextInt(height / 2);
            for (int dy = 0; dy < vineLen; dy++) {
                if (context.getBlockState(x, y + dy, z - 1).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x, y + dy, z - 1, VINE_SOUTH);
                }
                if (context.getBlockState(x + 1, y + dy, z - 1).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x + 1, y + dy, z - 1, VINE_SOUTH);
                }
            }
        }

        // South side of trunk (z+2)
        if (random.nextFloat() < 0.7f) {
            int vineLen = height / 2 + random.nextInt(height / 2);
            for (int dy = 0; dy < vineLen; dy++) {
                if (context.getBlockState(x, y + dy, z + 2).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x, y + dy, z + 2, VINE_NORTH);
                }
                if (context.getBlockState(x + 1, y + dy, z + 2).getBlockType() == BlockTypes.AIR) {
                    placeVine(context, x + 1, y + dy, z + 2, VINE_NORTH);
                }
            }
        }
    }

    /**
     * Place a vine block with direction.
     */
    protected void placeVine(WorldFeatureContext context, int x, int y, int z, int directionBits) {
        var vineState = BlockTypes.VINE.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, directionBits);
        context.setBlockState(x, y, z, vineState);
    }

    /**
     * Place cocoa beans on the trunk.
     */
    protected void placeCocoa(WorldFeatureContext context, int x, int y, int z, int height) {
        var random = ThreadLocalRandom.current();
        int cocoaCount = 1 + random.nextInt(3);

        int[][] offsets = {{-1, 0}, {2, 0}, {0, -1}, {0, 2}};
        for (int i = 0; i < cocoaCount; i++) {
            int[] offset = offsets[random.nextInt(offsets.length)];
            int cy = y + 3 + random.nextInt(Math.max(1, height - 6));
            int cx = x + offset[0];
            int cz = z + offset[1];

            if (context.getBlockState(cx, cy, cz).getBlockType() == BlockTypes.AIR) {
                context.setBlockState(cx, cy, cz, BlockTypes.COCOA.getDefaultState());
            }
        }
    }
}
