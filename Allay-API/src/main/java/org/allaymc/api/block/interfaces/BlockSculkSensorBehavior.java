package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

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
