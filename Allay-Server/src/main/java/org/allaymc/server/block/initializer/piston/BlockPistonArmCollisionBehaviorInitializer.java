package org.allaymc.server.block.initializer.piston;

import org.allaymc.api.block.interfaces.piston.BlockPistonArmCollisionBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPistonArmCollisionBehaviorInitializer {
  static void init() {
    BlockTypes.PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
            .builder(BlockPistonArmCollisionBehavior.class)
            .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
