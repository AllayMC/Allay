package org.allaymc.server.block.initializer.amethystbud;

import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmallAmethystBudBehaviorInitializer {
  static void init() {
    BlockTypes.SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockSmallAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }
}
