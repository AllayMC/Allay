package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockWhiteShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockWhiteShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX)
            .build();
  }
}
