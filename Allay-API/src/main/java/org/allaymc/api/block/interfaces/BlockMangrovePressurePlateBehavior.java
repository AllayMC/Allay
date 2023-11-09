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
public interface BlockMangrovePressurePlateBehavior extends BlockBehavior {
  BlockType<BlockMangrovePressurePlateBehavior> MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
