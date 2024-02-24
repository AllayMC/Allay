package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockBlackShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBlackShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
            .build();
  }
}
