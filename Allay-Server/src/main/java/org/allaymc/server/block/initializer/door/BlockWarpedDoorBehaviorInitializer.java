package org.allaymc.server.block.initializer.door;

import org.allaymc.api.block.interfaces.door.BlockWarpedDoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedDoorBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockWarpedDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
