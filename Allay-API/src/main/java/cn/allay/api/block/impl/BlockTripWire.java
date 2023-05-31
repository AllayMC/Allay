package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
