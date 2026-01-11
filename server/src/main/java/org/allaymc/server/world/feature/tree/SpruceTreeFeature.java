package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Spruce tree feature implementation.
 * Generates standard spruce trees with conical shape.
 *
 * @author daoge_cmd
 */
public class SpruceTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:spruce_tree");

    public SpruceTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.SPRUCE_LOG,
                BlockTypes.SPRUCE_LEAVES,
                BlockTypes.SPRUCE_SAPLING,
                6, 9
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

        // Calculate leaf parameters (matching NKMOT algorithm)
        int topSize = height - (1 + random.nextInt(2));  // Number of leaf layers from top
        int lRadius = 2 + random.nextInt(2);  // Maximum leaf radius (2-3)

        // Place trunk (slightly shorter than full height)
        int trunkHeight = height - random.nextInt(2);
        for (int dy = 0; dy < trunkHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        // Place leaves using NKMOT stepped algorithm
        placeSpruceLeaves(context, x, y, z, height, topSize, lRadius, random);

        return true;
    }

    /**
     * Place spruce leaves in a stepped conical pattern.
     * Algorithm matches NKMOT's ObjectSpruceTree.placeLeaves().
     */
    protected void placeSpruceLeaves(WorldFeatureContext context, int x, int y, int z,
                                      int treeHeight, int topSize, int lRadius, ThreadLocalRandom random) {
        int radius = random.nextInt(2);  // Start radius: 0 or 1
        int maxR = 1;
        int minR = 0;

        // Place leaves from top going down
        for (int yy = 0; yy <= topSize; yy++) {
            int leafY = y + treeHeight - yy;

            for (int xx = x - radius; xx <= x + radius; xx++) {
                int xOff = Math.abs(xx - x);
                for (int zz = z - radius; zz <= z + radius; zz++) {
                    int zOff = Math.abs(zz - z);

                    // Skip corners when radius > 0
                    if (xOff == radius && zOff == radius && radius > 0) {
                        continue;
                    }

                    // Place leaf if not solid
                    placeLeaves(context, xx, leafY, zz);
                }
            }

            // Update radius for next layer (stepped pattern)
            if (radius >= maxR) {
                radius = minR;
                minR = 1;
                if (++maxR > lRadius) {
                    maxR = lRadius;
                }
            } else {
                radius++;
            }
        }
    }
}
