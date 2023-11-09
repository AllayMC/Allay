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
public interface BlockDetectorRailBehavior extends BlockBehavior {
  BlockType<BlockDetectorRailBehavior> DETECTOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockDetectorRailBehavior.class)
          .vanillaBlock(VanillaBlockId.DETECTOR_RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
          .build();
}
