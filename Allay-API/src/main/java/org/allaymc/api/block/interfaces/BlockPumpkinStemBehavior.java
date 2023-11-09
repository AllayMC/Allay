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
public interface BlockPumpkinStemBehavior extends BlockBehavior {
  BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinStemBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .build();
}
