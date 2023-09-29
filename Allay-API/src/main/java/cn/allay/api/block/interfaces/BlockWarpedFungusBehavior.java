package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedFungusBehavior extends BlockBehavior {
  BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FUNGUS)
          .build();
}
