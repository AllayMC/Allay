package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockWarpedButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedButtonBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockWarpedButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
