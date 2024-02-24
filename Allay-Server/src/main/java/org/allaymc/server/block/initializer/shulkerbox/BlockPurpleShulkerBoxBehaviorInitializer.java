package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockPurpleShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockPurpleShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
            .build();
  }
}
