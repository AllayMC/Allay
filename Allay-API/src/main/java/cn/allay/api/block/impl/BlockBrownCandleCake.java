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
public interface BlockBrownCandleCake extends Block {
    BlockType<BlockBrownCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleCake.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build();
}
