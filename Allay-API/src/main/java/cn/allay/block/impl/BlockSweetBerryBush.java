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
public interface BlockSweetBerryBush extends Block {
    BlockType<BlockSweetBerryBush> TYPE = BlockTypeBuilder
            .builder(BlockSweetBerryBush.class)
            .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH, true)
            .withProperties(VanillaBlockPropertyTypes.GROWTH)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
