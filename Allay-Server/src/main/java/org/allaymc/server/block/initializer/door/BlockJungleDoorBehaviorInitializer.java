package org.allaymc.server.block.initializer.door;

import org.allaymc.api.block.interfaces.door.BlockJungleDoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJungleDoorBehaviorInitializer {
  static void init() {
    BlockTypes.JUNGLE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockJungleDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
