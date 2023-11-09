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
public interface BlockStrippedWarpedStemBehavior extends BlockBehavior {
  BlockType<BlockStrippedWarpedStemBehavior> STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedStemBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
