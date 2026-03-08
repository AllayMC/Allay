package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Jungle tree feature implementation.
 * Generates small jungle trees with vines and cocoa beans.
 *
 * @author daoge_cmd
 */
public class JungleTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:jungle_tree");

    // Vine direction bits:
    // 1 = south face, 2 = west face, 4 = north face, 8 = east face
    protected static final int VINE_SOUTH = 1;
    protected static final int VINE_WEST = 2;
    protected static final int VINE_NORTH = 4;
    protected static final int VINE_EAST = 8;

    public JungleTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.JUNGLE_LOG,
                BlockTypes.JUNGLE_LEAVES,
                BlockTypes.JUNGLE_SAPLING,
                4, 8
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

        // Place leaves first (before trunk, so vines can be placed correctly)
        int leafStart = y + height - 3;
        int leafEnd = y + height;
        for (int yy = leafStart; yy <= leafEnd; yy++) {
            int yOff = yy - leafEnd;
            int radius = 1 - yOff / 2;

            for (int xx = x - radius; xx <= x + radius; xx++) {
                int xOff = Math.abs(xx - x);
                for (int zz = z - radius; zz <= z + radius; zz++) {
                    int zOff = Math.abs(zz - z);
                    // Skip corners randomly
                    if (xOff != radius || zOff != radius || (random.nextInt(2) != 0 && yOff != 0)) {
                        placeLeaves(context, xx, yy, zz);
                    }
                }
            }
        }

        // Place trunk with vines
        for (int dy = 0; dy < height; dy++) {
            placeLog(context, x, y + dy, z);

            // Add vines on trunk (2/3 chance per side)
            if (dy > 0) {
                // West side of trunk (vine attached to east face)
                if (random.nextInt(3) > 0 && isAir(context, x - 1, y + dy, z)) {
                    placeVine(context, x - 1, y + dy, z, VINE_EAST);
                }
                // East side of trunk (vine attached to west face)
                if (random.nextInt(3) > 0 && isAir(context, x + 1, y + dy, z)) {
                    placeVine(context, x + 1, y + dy, z, VINE_WEST);
                }
                // North side of trunk (vine attached to south face)
                if (random.nextInt(3) > 0 && isAir(context, x, y + dy, z - 1)) {
                    placeVine(context, x, y + dy, z - 1, VINE_SOUTH);
                }
                // South side of trunk (vine attached to north face)
                if (random.nextInt(3) > 0 && isAir(context, x, y + dy, z + 1)) {
                    placeVine(context, x, y + dy, z + 1, VINE_NORTH);
                }
            }
        }

        // Add hanging vines from leaves
        for (int yy = leafStart; yy <= leafEnd; yy++) {
            int yOff = yy - leafEnd;
            int radius = 2 - yOff / 2;

            for (int xx = x - radius; xx <= x + radius; xx++) {
                for (int zz = z - radius; zz <= z + radius; zz++) {
                    if (context.getBlockState(xx, yy, zz).getBlockType() == leavesType) {
                        // West (vine attached to east face)
                        if (random.nextInt(4) == 0 && isAir(context, xx - 1, yy, zz)) {
                            addHangingVine(context, xx - 1, yy, zz, VINE_EAST);
                        }
                        // East (vine attached to west face)
                        if (random.nextInt(4) == 0 && isAir(context, xx + 1, yy, zz)) {
                            addHangingVine(context, xx + 1, yy, zz, VINE_WEST);
                        }
                        // North (vine attached to south face)
                        if (random.nextInt(4) == 0 && isAir(context, xx, yy, zz - 1)) {
                            addHangingVine(context, xx, yy, zz - 1, VINE_SOUTH);
                        }
                        // South (vine attached to north face)
                        if (random.nextInt(4) == 0 && isAir(context, xx, yy, zz + 1)) {
                            addHangingVine(context, xx, yy, zz + 1, VINE_NORTH);
                        }
                    }
                }
            }
        }

        // Add cocoa beans (1/5 chance if tall enough)
        if (random.nextInt(5) == 0 && height > 5) {
            for (int i = 0; i < 2; i++) {
                for (int side = 0; side < 4; side++) {
                    if (random.nextInt(4 - i) == 0) {
                        int cx = x;
                        int cz = z;
                        switch (side) {
                            case 0 -> cz -= 1;
                            case 1 -> cz += 1;
                            case 2 -> cx -= 1;
                            case 3 -> cx += 1;
                        }
                        int cy = y + height - 5 + i;
                        if (isAir(context, cx, cy, cz)) {
                            // Cocoa with random age (0-2)
                            context.setBlockState(cx, cy, cz, BlockTypes.COCOA.getDefaultState());
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Check if position is air.
     */
    protected boolean isAir(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType() == BlockTypes.AIR;
    }

    /**
     * Place a vine block with direction.
     *
     * @param directionBits the vine direction bits (1=south, 2=west, 4=north, 8=east)
     */
    protected void placeVine(WorldFeatureContext context, int x, int y, int z, int directionBits) {
        var vineState = BlockTypes.VINE.getDefaultState()
                .setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, directionBits);
        context.setBlockState(x, y, z, vineState);
    }

    /**
     * Add hanging vines down from a position.
     *
     * @param directionBits the vine direction bits
     */
    protected void addHangingVine(WorldFeatureContext context, int x, int y, int z, int directionBits) {
        placeVine(context, x, y, z, directionBits);
        // Hang down up to 4 blocks
        for (int i = 1; i <= 4; i++) {
            if (!isAir(context, x, y - i, z)) {
                break;
            }
            placeVine(context, x, y - i, z, directionBits);
        }
    }
}
