package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStrippedCrimsonStemBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedCrimsonStemBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
