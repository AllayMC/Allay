package org.allaymc.api.block.interfaces.wall;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTileWallBehavior extends BlockBehavior {
  BlockType<BlockDeepslateTileWallBehavior> DEEPSLATE_TILE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileWallBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .build();
}