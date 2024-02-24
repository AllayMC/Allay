package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDropperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDropperBehaviorInitializer {
  static void init() {
    BlockTypes.DROPPER_TYPE = BlockTypeBuilder
            .builder(BlockDropperBehavior.class)
            .vanillaBlock(VanillaBlockId.DROPPER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }
}
