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
public interface BlockRedstoneWire extends Block {
    BlockType<BlockRedstoneWire> TYPE = BlockTypeBuilder
            .builder(BlockRedstoneWire.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_WIRE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
