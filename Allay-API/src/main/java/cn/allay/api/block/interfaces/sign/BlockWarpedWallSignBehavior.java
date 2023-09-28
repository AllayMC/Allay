package cn.allay.api.block.interfaces.sign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedWallSignBehavior extends BlockBehavior {
  BlockType<BlockWarpedWallSignBehavior> WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWallSignBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
