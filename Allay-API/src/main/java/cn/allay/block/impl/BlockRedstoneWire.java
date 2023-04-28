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
public interface BlockRedstoneWire extends Block {
    BlockType<BlockRedstoneWire> TYPE = BlockTypeBuilder
            .builder(BlockRedstoneWire.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_WIRE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
