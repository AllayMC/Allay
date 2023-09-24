package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedExposedCopperBehavior> WAXED_EXPOSED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER)
            .build();
}
