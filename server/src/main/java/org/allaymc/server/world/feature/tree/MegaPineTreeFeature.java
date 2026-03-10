package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mega pine tree feature implementation.
 *
 * @author daoge_cmd
 */
public class MegaPineTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:mega_pine_tree");

    public MegaPineTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.SPRUCE_LOG,
                BlockTypes.SPRUCE_LEAVES,
                BlockTypes.SPRUCE_SAPLING,
                13, 29
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(13, 2, 14);

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                if (!canGrowOn(context.getBlockState(x + dx, y - 1, z + dz))) {
                    return false;
                }
            }
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(
                context,
                height,
                x,
                y,
                z,
                (treeHeight, currentHeight) -> currentHeight < 1 ? 1 : 2,
                false
        );
        if (maxFreeTreeHeight < height) {
            return false;
        }

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                placeDirtUnder(context, x + dx, y - 1, z + dz);
            }
        }

        var placedLogs = new ArrayList<TreePos>();
        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLogIfValid(context, x, y + dy, z, placedLogs);
            if (dy < maxFreeTreeHeight - 1) {
                placeLogIfValid(context, x + 1, y + dy, z, placedLogs);
                placeLogIfValid(context, x + 1, y + dy, z + 1, placedLogs);
                placeLogIfValid(context, x, y + dy, z + 1, placedLogs);
            }
        }

        var placedLeaves = new ArrayList<TreePos>();
        placeMegaPineFoliage(
                context,
                new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, true),
                3 + random.nextInt(5),
                0,
                0,
                placedLeaves
        );
        placePodzol(context, placedLogs);
        return true;
    }
}
