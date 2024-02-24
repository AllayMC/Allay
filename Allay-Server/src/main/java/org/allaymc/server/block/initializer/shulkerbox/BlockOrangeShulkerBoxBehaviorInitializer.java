package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockOrangeShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockOrangeShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
            .build();
  }
}
