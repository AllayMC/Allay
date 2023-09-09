package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagentaCandleBehavior extends BlockBehavior {
    BlockType<BlockMagentaCandleBehavior> MAGENTA_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
