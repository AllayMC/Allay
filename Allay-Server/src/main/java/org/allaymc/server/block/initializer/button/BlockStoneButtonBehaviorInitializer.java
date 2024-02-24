package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockStoneButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneButtonBehaviorInitializer {
  static void init() {
    BlockTypes.STONE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockStoneButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
