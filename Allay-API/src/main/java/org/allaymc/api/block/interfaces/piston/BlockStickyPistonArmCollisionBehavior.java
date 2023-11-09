package org.allaymc.api.block.interfaces.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStickyPistonArmCollisionBehavior extends BlockBehavior {
  BlockType<BlockStickyPistonArmCollisionBehavior> STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonArmCollisionBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
