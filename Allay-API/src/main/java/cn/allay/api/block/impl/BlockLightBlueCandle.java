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
public interface BlockLightBlueCandle extends Block {
    BlockType<BlockLightBlueCandle> TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandle.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE, true)
            .withProperties(VanillaBlockPropertyTypes.CANDLES,
                    VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
