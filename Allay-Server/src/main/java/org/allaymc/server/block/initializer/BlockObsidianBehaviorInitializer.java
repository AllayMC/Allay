package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockObsidianBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockObsidianBehaviorInitializer {
  static void init() {
    BlockTypes.OBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockObsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.OBSIDIAN)
            .build();
  }
}
