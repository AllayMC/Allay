package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSculkSensorBehavior extends BlockBehavior {
    BlockType<BlockSculkSensorBehavior> SCULK_SENSOR_TYPE = BlockTypeBuilder
            .builder(BlockSculkSensorBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_SENSOR)
            .setProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
            .build();
}
