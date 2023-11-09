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
public interface BlockDaylightDetectorBehavior extends BlockBehavior {
  BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
