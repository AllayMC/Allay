package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockLimeShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLimeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
            .build();
  }
}
