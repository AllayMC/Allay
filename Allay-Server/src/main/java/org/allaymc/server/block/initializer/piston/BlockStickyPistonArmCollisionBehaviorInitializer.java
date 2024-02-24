package org.allaymc.server.block.initializer.piston;

import org.allaymc.api.block.interfaces.piston.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStickyPistonArmCollisionBehaviorInitializer {
  static void init() {
    BlockTypes.STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
            .builder(BlockStickyPistonArmCollisionBehavior.class)
            .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
