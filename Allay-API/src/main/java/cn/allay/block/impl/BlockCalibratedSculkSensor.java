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
public interface BlockCalibratedSculkSensor extends Block {
    BlockType<BlockCalibratedSculkSensor> TYPE = BlockTypeBuilder
            .builder(BlockCalibratedSculkSensor.class)
            .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
            .property(VanillaBlockPropertyTypes.DIRECTION,
                    VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
}
