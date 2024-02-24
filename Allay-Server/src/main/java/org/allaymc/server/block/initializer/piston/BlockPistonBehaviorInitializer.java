package org.allaymc.server.block.initializer.piston;

import org.allaymc.api.block.interfaces.piston.BlockPistonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPistonBehaviorInitializer {
  static void init() {
    BlockTypes.PISTON_TYPE = BlockTypeBuilder
            .builder(BlockPistonBehavior.class)
            .vanillaBlock(VanillaBlockId.PISTON)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
