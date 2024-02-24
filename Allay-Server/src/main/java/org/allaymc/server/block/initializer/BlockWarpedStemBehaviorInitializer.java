package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedStemBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedStemBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
