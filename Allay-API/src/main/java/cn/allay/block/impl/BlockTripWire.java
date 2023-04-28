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
public interface BlockTripWire extends Block {
    BlockType<BlockTripWire> TYPE = BlockTypeBuilder
            .builder(BlockTripWire.class)
            .vanillaBlock(VanillaBlockId.TRIP_WIRE, true)
            .withProperties(VanillaBlockPropertyTypes.ATTACHED_BIT,
                    VanillaBlockPropertyTypes.DISARMED_BIT,
                    VanillaBlockPropertyTypes.POWERED_BIT,
                    VanillaBlockPropertyTypes.SUSPENDED_BIT)
            .addBasicComponents()
            .build();
}
