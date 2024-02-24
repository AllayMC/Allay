package org.allaymc.server.block.initializer.door;

import org.allaymc.api.block.interfaces.door.BlockCopperDoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperDoorBehaviorInitializer {
  static void init() {
    BlockTypes.COPPER_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockCopperDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
