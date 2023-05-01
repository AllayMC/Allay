package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackCandleCake extends Block {
    BlockType<BlockBlackCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockBlackCandleCake.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
