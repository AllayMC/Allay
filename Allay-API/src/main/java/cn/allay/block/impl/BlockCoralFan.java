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
public interface BlockCoralFan extends Block {
    BlockType<BlockCoralFan> TYPE = BlockTypeBuilder
            .builder(BlockCoralFan.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR,
                    VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION)
            .addBasicComponents()
            .build();
}
