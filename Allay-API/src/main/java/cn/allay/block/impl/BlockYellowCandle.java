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
public interface BlockYellowCandle extends Block {
    BlockType<BlockYellowCandle> TYPE = BlockTypeBuilder
            .builder(BlockYellowCandle.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE)
            .property(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .build();
}
