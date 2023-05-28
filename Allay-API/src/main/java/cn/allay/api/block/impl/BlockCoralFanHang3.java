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
public interface BlockCoralFanHang3 extends Block {
    BlockType<BlockCoralFanHang3> TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang3.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION,
                    VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
