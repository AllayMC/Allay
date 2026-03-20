package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;

/**
 * Straight-trunk tree with blob foliage (oak, birch, jungle, tall birch shape).
 * <p>
 * Configurable via constructor parameters for easy reuse with different
 * block types and height ranges.
 *
 * @author daoge_cmd
 */
public class StraightBlobTreeFeature extends TreeWorldFeature {

    public static final Identifier OAK_IDENTIFIER = new Identifier("minecraft:oak_tree");
    public static final Identifier BIRCH_IDENTIFIER = new Identifier("minecraft:birch_tree");
    public static final Identifier JUNGLE_IDENTIFIER = new Identifier("minecraft:jungle_tree");
    public static final Identifier TALL_BIRCH_IDENTIFIER = new Identifier("minecraft:tall_birch_tree");

    private final int baseHeight;
    private final int heightRandA;
    private final int heightRandB;

    public StraightBlobTreeFeature(
            Identifier identifier,
            BlockType<?> logType,
            BlockType<?> leavesType,
            int baseHeight,
            int heightRandA,
            int heightRandB
    ) {
        super(identifier, logType, leavesType);
        this.baseHeight = baseHeight;
        this.heightRandA = heightRandA;
        this.heightRandB = heightRandB;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        int height = calculateHeight(baseHeight, heightRandA, heightRandB);
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
