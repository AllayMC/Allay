package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchDoor extends Block {
    BlockType<BlockBirchDoor> TYPE = BlockTypeBuilder
            .builder(BlockBirchDoor.class)
            .vanillaBlock(VanillaBlockId.BIRCH_DOOR, true)
            .withProperties(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.DOOR_HINGE_BIT,
                    VanillaBlockPropertyTypes.OPEN_BIT,
                    VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
