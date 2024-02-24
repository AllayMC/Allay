package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherSproutsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherSproutsBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_SPROUTS_TYPE = BlockTypeBuilder
            .builder(BlockNetherSproutsBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_SPROUTS)
            .build();
  }
}
