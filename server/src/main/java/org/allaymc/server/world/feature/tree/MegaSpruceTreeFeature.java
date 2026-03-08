package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Mega spruce tree feature implementation.
 * Generates large 2x2 spruce trees with dense foliage.
 *
 * @author daoge_cmd
 */
public class MegaSpruceTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:mega_spruce_tree");

    // Leaf parameters matching NKMOT's ObjectBigSpruceTree(0.45f, 3)
    protected static final float LEAF_START_HEIGHT_MULTIPLIER = 0.45f;
    protected static final int BASE_LEAF_RADIUS = 3;

    public MegaSpruceTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.SPRUCE_LOG,
                BlockTypes.SPRUCE_LEAVES,
                BlockTypes.SPRUCE_SAPLING,
                15, 29
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

        // Place podzol around base
        placePodzol(context, x, y - 1, z);

        // Place 2x2 trunk
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx <= 1; dx++) {
                for (int dz = 0; dz <= 1; dz++) {
                    placeLog(context, x + dx, y + dy, z + dz);
                }
            }
        }

        // Calculate topSize (number of leaf layers from top)
        int topSize = height - (int) (height * LEAF_START_HEIGHT_MULTIPLIER);

        // Place leaves using NKMOT's stepped algorithm for big trees
        placeBigSpruceLeaves(context, x, y, z, height, topSize, BASE_LEAF_RADIUS, random);

        return true;
    }

    /**
     * Place mega spruce leaves in a stepped conical pattern.
     * Algorithm matches NKMOT's ObjectBigSpruceTree.placeBigLeaves().
     */
    protected void placeBigSpruceLeaves(WorldFeatureContext context, int x, int y, int z,
                                         int treeHeight, int topSize, int baseLeafRadius, ThreadLocalRandom random) {
        // Center is between the 4 trunks
        int centerX = x;
        int centerZ = z;

        int maxRadius = baseLeafRadius + 1;
        int radius = 2;
        int maxR = 2;
        int minR = 1;

        // Place leaves from top going down
        for (int yy = 0; yy <= topSize + 2; yy++) {
            int leafY = y + treeHeight - yy;

            for (int xx = centerX - radius; xx <= centerX + radius + 1; xx++) {
                for (int zz = centerZ - radius; zz <= centerZ + radius + 1; zz++) {
                    int xOff = Math.abs(xx - centerX);
                    int zOff = Math.abs(zz - centerZ);

                    // Adjust for 2x2 center
                    if (xx > centerX) xOff = Math.abs(xx - centerX - 1);
                    if (zz > centerZ) zOff = Math.abs(zz - centerZ - 1);

                    // Skip corners when radius > 1
                    if (xOff >= radius && zOff >= radius && radius > 1) {
                        continue;
                    }

                    // Skip trunk positions
                    if (xx >= x && xx <= x + 1 && zz >= z && zz <= z + 1 && leafY < y + treeHeight) {
                        continue;
                    }

                    placeLeaves(context, xx, leafY, zz);
                }
            }

            // Update radius for next layer (stepped pattern)
            if (radius >= maxR) {
                radius = minR;
                minR = 1;
                if (++maxR > maxRadius) {
                    maxR = maxRadius;
                }
            } else {
                radius++;
            }
        }
    }

    /**
     * Place podzol around the tree base.
     */
    protected void placePodzol(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int radius = 6;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                float calcX = dx + 0.5f;
                float calcZ = dz + 0.5f;
                float calcRad = radius + 0.8f;

                if (calcX * calcX + calcZ * calcZ < calcRad * calcRad) {
                    var block = context.getBlockState(x + dx, y, z + dz);
                    if (block.getBlockType() == BlockTypes.GRASS_BLOCK ||
                        block.getBlockType() == BlockTypes.DIRT) {
                        context.setBlockState(x + dx, y, z + dz, BlockTypes.PODZOL.getDefaultState());
                    }
                }
            }
        }
    }
}
