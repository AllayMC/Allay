package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockRedShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.RED_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockRedShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
            .build();
  }
}
