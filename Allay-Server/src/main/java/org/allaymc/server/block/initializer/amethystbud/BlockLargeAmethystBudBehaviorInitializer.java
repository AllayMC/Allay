package org.allaymc.server.block.initializer.amethystbud;

import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLargeAmethystBudBehaviorInitializer {
  static void init() {
    BlockTypes.LARGE_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockLargeAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }
}
