package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitPumpkinBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitPumpkinBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockLitPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
