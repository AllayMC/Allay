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
public interface BlockPolishedBlackstoneWallBehavior extends BlockBehavior {
  BlockType<BlockPolishedBlackstoneWallBehavior> POLISHED_BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneWallBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_WALL)
          .setProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_POST_BIT)
          .build();
}
