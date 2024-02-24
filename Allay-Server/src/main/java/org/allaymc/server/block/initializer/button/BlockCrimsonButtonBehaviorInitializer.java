package org.allaymc.server.block.initializer.button;

import org.allaymc.api.block.interfaces.button.BlockCrimsonButtonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonButtonBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_BUTTON_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonButtonBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON)
            .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
