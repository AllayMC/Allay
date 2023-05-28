package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleCandleCake extends Block {
    BlockType<BlockPurpleCandleCake> TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleCake.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE, true)
            .withProperties(VanillaBlockPropertyTypes.LIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
