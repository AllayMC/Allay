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
public interface BlockPinkCandleBehavior extends BlockBehavior {
    BlockType<BlockPinkCandleBehavior> PINK_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
}
