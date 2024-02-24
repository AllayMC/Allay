package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDispenserBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDispenserBehaviorInitializer {
  static void init() {
    BlockTypes.DISPENSER_TYPE = BlockTypeBuilder
            .builder(BlockDispenserBehavior.class)
            .vanillaBlock(VanillaBlockId.DISPENSER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }
}
