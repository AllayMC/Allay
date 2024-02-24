package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockUndyedShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUndyedShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockUndyedShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
            .build();
  }
}
