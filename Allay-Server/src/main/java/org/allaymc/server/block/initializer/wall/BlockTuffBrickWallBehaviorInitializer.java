package org.allaymc.server.block.initializer.wall;

import org.allaymc.api.block.interfaces.wall.BlockTuffBrickWallBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTuffBrickWallBehaviorInitializer {
  static void init() {
    BlockTypes.TUFF_BRICK_WALL_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickWallBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }
}
