package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonStemBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonStemBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
