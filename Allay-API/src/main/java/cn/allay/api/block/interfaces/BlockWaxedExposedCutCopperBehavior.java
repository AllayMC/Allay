package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCutCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedExposedCutCopperBehavior> WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER)
            .build();
}
