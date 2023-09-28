package cn.allay.api.block.interfaces.wall;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCobblestoneWallBehavior extends BlockBehavior {
  BlockType<BlockCobblestoneWallBehavior> COBBLESTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_BLOCK_TYPE, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .build();
}
