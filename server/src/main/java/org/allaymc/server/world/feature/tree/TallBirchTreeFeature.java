package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
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
        int height = calculateHeight(5, 2, 6);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 1, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        placeBlobFoliage(context, new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, false), 3, 2, 0, new ArrayList<>());
        return true;
    }
}
