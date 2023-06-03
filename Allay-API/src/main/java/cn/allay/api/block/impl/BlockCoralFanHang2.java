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
public interface BlockCoralFanHang2 extends Block {
    BlockType<BlockCoralFanHang2> TYPE = BlockTypeBuilder
            .builder(BlockCoralFanHang2.class)
            .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2, true)
            .withProperties(VanillaBlockPropertyTypes.CORAL_DIRECTION,
                    VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .addBasicComponents()
            .build();
}
