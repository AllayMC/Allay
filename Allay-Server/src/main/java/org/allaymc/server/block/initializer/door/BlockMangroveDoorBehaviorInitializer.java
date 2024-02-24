package org.allaymc.server.block.initializer.door;

import org.allaymc.api.block.interfaces.door.BlockMangroveDoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveDoorBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_DOOR_TYPE = BlockTypeBuilder
            .builder(BlockMangroveDoorBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_DOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
