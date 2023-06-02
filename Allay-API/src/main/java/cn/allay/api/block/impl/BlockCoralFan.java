package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
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
