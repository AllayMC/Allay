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
public interface BlockDaylightDetectorInvertedBehavior extends BlockBehavior {
  BlockType<BlockDaylightDetectorInvertedBehavior> DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorInvertedBehavior.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
