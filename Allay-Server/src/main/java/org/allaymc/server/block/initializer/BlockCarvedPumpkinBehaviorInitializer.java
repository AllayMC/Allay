package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCarvedPumpkinBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCarvedPumpkinBehaviorInitializer {
  static void init() {
    BlockTypes.CARVED_PUMPKIN_TYPE = BlockTypeBuilder
            .builder(BlockCarvedPumpkinBehavior.class)
            .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
