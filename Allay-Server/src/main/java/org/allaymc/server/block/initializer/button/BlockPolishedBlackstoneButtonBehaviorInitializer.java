package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockPolishedBlackstoneButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneButtonBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
