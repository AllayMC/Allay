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
public interface BlockLightGrayCandleCake extends Block {
    BlockType<BlockLightGrayCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleCake.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE)
            .property(VanillaBlockPropertyTypes.LIT)
            .build();
}
