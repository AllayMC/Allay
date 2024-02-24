package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStrippedWarpedStemBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedWarpedStemBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
