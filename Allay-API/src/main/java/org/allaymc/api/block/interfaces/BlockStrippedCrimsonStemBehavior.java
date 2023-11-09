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
public interface BlockStrippedCrimsonStemBehavior extends BlockBehavior {
  BlockType<BlockStrippedCrimsonStemBehavior> STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
