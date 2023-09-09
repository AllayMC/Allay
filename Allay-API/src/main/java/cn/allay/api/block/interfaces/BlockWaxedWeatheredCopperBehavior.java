package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedWeatheredCopperBehavior> WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER)
            .build();
}
