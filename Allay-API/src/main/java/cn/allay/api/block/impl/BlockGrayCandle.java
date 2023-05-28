package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayCandle extends Block {
    BlockType<BlockGrayCandle> TYPE = BlockTypeBuilder
            .builder(BlockGrayCandle.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE, true)
            .withProperties(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
