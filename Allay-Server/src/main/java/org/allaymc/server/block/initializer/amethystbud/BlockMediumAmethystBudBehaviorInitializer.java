package org.allaymc.server.block.initializer.amethystbud;

import org.allaymc.api.block.interfaces.amethystbud.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMediumAmethystBudBehaviorInitializer {
  static void init() {
    BlockTypes.MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
            .builder(BlockMediumAmethystBudBehavior.class)
            .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }
}
