package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
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
        int height = calculateHeight(5, 2, 1);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 2 ? 0 : 2, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        int foliageHeight = Math.max(4, maxFreeTreeHeight - (1 + random.nextInt(2)));
        int foliageRadius = 2 + random.nextInt(2);
        int offset = random.nextInt(3);
        placeSpruceFoliage(
                context,
                new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, false),
                foliageHeight,
                foliageRadius,
                offset,
                new ArrayList<>()
        );
        return true;
    }
}
