package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockGreenShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockGreenShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
            .build();
  }
}
