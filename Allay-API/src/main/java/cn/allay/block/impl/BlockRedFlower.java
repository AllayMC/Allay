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
public interface BlockRedFlower extends Block {
    BlockType<BlockRedFlower> TYPE = BlockTypeBuilder
            .builder(BlockRedFlower.class)
            .vanillaBlock(VanillaBlockId.RED_FLOWER, true)
            .withProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
