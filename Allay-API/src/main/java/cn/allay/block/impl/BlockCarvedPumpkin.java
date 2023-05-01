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
public interface BlockCarvedPumpkin extends Block {
    BlockType<BlockCarvedPumpkin> TYPE = BlockTypeBuilder
            .builder(BlockCarvedPumpkin.class)
            .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
