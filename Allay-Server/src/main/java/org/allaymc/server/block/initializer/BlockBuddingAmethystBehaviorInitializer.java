package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBuddingAmethystBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBuddingAmethystBehaviorInitializer {
  static void init() {
    BlockTypes.BUDDING_AMETHYST_TYPE = BlockTypeBuilder
            .builder(BlockBuddingAmethystBehavior.class)
            .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST)
            .build();
  }
}
