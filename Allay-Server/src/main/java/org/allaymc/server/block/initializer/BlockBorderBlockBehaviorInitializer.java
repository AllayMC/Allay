package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBorderBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBorderBlockBehaviorInitializer {
  static void init() {
    BlockTypes.BORDER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBorderBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BORDER_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
            .build();
  }
}
