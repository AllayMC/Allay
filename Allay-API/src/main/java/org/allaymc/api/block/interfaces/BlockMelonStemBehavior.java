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
public interface BlockMelonStemBehavior extends BlockBehavior {
  BlockType<BlockMelonStemBehavior> MELON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockMelonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_STEM)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .build();
}
