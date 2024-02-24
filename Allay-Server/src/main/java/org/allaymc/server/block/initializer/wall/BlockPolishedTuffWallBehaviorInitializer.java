package org.allaymc.server.block.initializer.wall;

import org.allaymc.api.block.interfaces.wall.BlockPolishedTuffWallBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedTuffWallBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_TUFF_WALL_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffWallBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF_WALL)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }
}
