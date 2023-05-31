package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeCandleCake extends Block {
    BlockType<BlockOrangeCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockOrangeCandleCake.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build();
}
