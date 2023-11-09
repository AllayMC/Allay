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
public interface BlockBambooPressurePlateBehavior extends BlockBehavior {
  BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
