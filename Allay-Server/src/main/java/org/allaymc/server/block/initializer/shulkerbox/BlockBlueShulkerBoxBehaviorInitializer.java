package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockBlueShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockBlueShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
            .build();
  }
}
