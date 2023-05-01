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
public interface BlockVine extends Block {
    BlockType<BlockVine> TYPE = BlockTypeBuilder
            .builder(BlockVine.class)
            .vanillaBlock(VanillaBlockId.VINE, true)
            .withProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
