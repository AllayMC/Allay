package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonHyphaeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonHyphaeBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonHyphaeBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
