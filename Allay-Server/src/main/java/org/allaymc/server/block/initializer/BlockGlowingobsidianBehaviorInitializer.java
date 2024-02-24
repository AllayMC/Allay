package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGlowingobsidianBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlowingobsidianBehaviorInitializer {
  static void init() {
    BlockTypes.GLOWINGOBSIDIAN_TYPE = BlockTypeBuilder
            .builder(BlockGlowingobsidianBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN)
            .build();
  }
}
