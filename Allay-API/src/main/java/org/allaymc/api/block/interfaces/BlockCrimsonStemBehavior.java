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
public interface BlockCrimsonStemBehavior extends BlockBehavior {
  BlockType<BlockCrimsonStemBehavior> CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStemBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
