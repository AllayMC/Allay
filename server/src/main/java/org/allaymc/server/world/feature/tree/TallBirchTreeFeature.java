package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Tall birch tree feature implementation.
 * Generates taller birch trees found in old growth birch forests.
 *
 * @author daoge_cmd
 */
public class TallBirchTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:tall_birch_tree");

    public TallBirchTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.BIRCH_LOG,
                BlockTypes.BIRCH_LEAVES,
                BlockTypes.BIRCH_SAPLING,
                10, 12
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

        // Place leaves - taller tree has more leaf layers
        int leafStart = height - 4;
        for (int dy = leafStart; dy <= height; dy++) {
            int radius;
            if (dy == height) {
                radius = 1;
            } else if (dy == leafStart) {
                radius = 1;
            } else if (dy == leafStart + 1) {
                radius = 2;
            } else {
                radius = 2;
            }

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    // Skip corners
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

        return true;
    }
}
