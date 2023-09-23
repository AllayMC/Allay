package cn.allay.api.block.interfaces.waxedcutcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedCutCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedCutCopperBehavior> WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER)
            .build();
}
