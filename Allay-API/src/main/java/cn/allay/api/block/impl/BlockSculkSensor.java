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
public interface BlockSculkSensor extends Block {
    BlockType<BlockSculkSensor> TYPE = BlockTypeBuilder
            .builder(BlockSculkSensor.class)
            .vanillaBlock(VanillaBlockId.SCULK_SENSOR, true)
            .withProperties(VanillaBlockPropertyTypes.POWERED_BIT)
            .addBasicComponents()
            .build();
}
