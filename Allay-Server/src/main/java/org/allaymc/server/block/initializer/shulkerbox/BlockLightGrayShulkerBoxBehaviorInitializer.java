package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockLightGrayShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
            .build();
  }
}
