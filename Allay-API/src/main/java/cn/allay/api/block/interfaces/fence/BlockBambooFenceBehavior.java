package cn.allay.api.block.interfaces.fence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBambooFenceBehavior extends BlockBehavior {
  BlockType<BlockBambooFenceBehavior> BAMBOO_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE)
          .build();
}
