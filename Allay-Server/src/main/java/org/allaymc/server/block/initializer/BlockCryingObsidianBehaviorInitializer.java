package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCryingObsidianBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCryingObsidianBehaviorInitializer {
  static void init() {
    BlockTypes.CRYING_OBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockCryingObsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN)
            .build();
  }
}
