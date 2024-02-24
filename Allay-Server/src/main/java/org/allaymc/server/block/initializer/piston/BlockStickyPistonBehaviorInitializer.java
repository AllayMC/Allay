package org.allaymc.server.block.initializer.piston;

import org.allaymc.api.block.interfaces.piston.BlockStickyPistonBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStickyPistonBehaviorInitializer {
  static void init() {
    BlockTypes.STICKY_PISTON_TYPE = BlockTypeBuilder
            .builder(BlockStickyPistonBehavior.class)
            .vanillaBlock(VanillaBlockId.STICKY_PISTON)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
