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
public interface BlockCoralFanHang extends Block {
    BlockType<BlockCoralFanHang> TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION,
                    VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
