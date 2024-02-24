package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockWoodenButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWoodenButtonBehaviorInitializer {
  static void init() {
    BlockTypes.WOODEN_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockWoodenButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
