package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockCyanShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockCyanShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
            .build();
  }
}
