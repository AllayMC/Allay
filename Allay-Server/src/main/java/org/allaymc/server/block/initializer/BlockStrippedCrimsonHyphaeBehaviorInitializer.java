package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStrippedCrimsonHyphaeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedCrimsonHyphaeBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
