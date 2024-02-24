package org.allaymc.server.block.initializer.shulkerbox;

import org.allaymc.api.block.interfaces.shulkerbox.BlockYellowShulkerBoxBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowShulkerBoxBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_SHULKER_BOX_TYPE = BlockTypeBuilder
            .builder(BlockYellowShulkerBoxBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX)
            .build();
  }
}
