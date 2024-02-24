package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockGrayShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGrayShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
            .build();
  }
}
