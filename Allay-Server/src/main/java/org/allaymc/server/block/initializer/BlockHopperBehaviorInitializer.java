package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHopperBehaviorInitializer {
  static void init() {
    BlockTypes.HOPPER_TYPE = BlockTypeBuilder
            .builder(BlockHopperBehavior.class)
            .vanillaBlock(VanillaBlockId.HOPPER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
  }
}
