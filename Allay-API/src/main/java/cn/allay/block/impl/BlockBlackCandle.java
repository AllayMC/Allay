package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackCandle extends Block {
    BlockType<BlockBlackCandle> TYPE = BlockTypeBuilder
            .builder(BlockBlackCandle.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE)
            .property(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .build();
}
