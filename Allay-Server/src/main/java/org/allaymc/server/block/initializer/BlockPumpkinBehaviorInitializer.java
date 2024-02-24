package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPumpkinBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPumpkinBehaviorInitializer {
  static void init() {
    BlockTypes.PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
