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
public interface BlockLimeCandle extends Block {
    BlockType<BlockLimeCandle> TYPE = BlockTypeBuilder
            .builder(BlockLimeCandle.class)
            .vanillaBlock(VanillaBlockId.LIME_CANDLE, true)
            .withProperties(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build();
}
