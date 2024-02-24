package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockPinkShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPinkShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
            .build();
  }
}
