package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Acacia tree feature implementation.
 * Generates acacia trees with diagonal trunk and flat canopy.
 *
 * @author daoge_cmd
 */
public class AcaciaTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:acacia_tree");

    public AcaciaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.ACACIA_LOG,
                BlockTypes.ACACIA_LEAVES,
                BlockTypes.ACACIA_SAPLING,
                5, 10
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

        // Direction for diagonal trunk
        int dirX = random.nextBoolean() ? 1 : -1;
        int dirZ = random.nextBoolean() ? 1 : -1;

        // Place trunk (with bend)
        int bendHeight = 2 + random.nextInt(2);
        int currentX = x;
        int currentZ = z;

        for (int dy = 0; dy < height; dy++) {
            placeLog(context, currentX, y + dy, currentZ);

            // Add bend after certain height
            if (dy == bendHeight) {
                currentX += dirX;
                placeLog(context, currentX, y + dy, currentZ);
            }
            if (dy == bendHeight + 1) {
                currentZ += dirZ;
                placeLog(context, currentX, y + dy, currentZ);
            }
        }

        // Place flat canopy at the top
        int canopyY = y + height - 1;
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                // Create circular flat top
                if (Math.abs(dx) == 2 && Math.abs(dz) == 2) {
                    continue;
                }
                placeLeaves(context, currentX + dx, canopyY, currentZ + dz);
                // Add some leaves below on edges
                if ((Math.abs(dx) == 2 || Math.abs(dz) == 2) && random.nextBoolean()) {
                    placeLeaves(context, currentX + dx, canopyY - 1, currentZ + dz);
                }
            }
        }

        // Top layer
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (Math.abs(dx) == 1 && Math.abs(dz) == 1 && random.nextBoolean()) {
                    continue;
                }
                placeLeaves(context, currentX + dx, canopyY + 1, currentZ + dz);
            }
        }

        return true;
    }
}
