package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockBrownShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBrownShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
            .build();
  }
}
