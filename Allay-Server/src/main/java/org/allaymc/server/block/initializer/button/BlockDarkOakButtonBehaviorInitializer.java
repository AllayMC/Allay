package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockDarkOakButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakButtonBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
