package org.allaymc.server.block.initializer.wall;

import org.allaymc.api.block.interfaces.wall.BlockPolishedDeepslateWallBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateWallBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslateWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }
}
