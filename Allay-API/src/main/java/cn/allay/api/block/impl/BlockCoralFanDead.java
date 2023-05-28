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
public interface BlockCoralFanDead extends Block {
    BlockType<BlockCoralFanDead> TYPE = BlockTypeBuilder
            .builder(BlockCoralFanDead.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR,
                    VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
