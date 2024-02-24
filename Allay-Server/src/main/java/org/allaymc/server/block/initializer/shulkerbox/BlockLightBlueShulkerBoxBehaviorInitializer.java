package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockLightBlueShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX)
            .build();
  }
}
