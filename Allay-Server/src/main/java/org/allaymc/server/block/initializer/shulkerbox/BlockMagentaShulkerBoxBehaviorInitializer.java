package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockMagentaShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockMagentaShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX)
            .build();
  }
}
