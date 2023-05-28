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
public interface BlockLectern extends Block {
    BlockType<BlockLectern> TYPE = BlockTypeBuilder
            .builder(BlockLectern.class)
            .vanillaBlock(VanillaBlockId.LECTERN, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.POWERED_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
