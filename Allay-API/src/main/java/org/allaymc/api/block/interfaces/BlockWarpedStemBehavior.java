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
public interface BlockWarpedStemBehavior extends BlockBehavior {
  BlockType<BlockWarpedStemBehavior> WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
