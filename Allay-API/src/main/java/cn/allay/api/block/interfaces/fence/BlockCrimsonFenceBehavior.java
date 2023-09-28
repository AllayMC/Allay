package cn.allay.api.block.interfaces.fence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFenceBehavior extends BlockBehavior {
  BlockType<BlockCrimsonFenceBehavior> CRIMSON_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE)
          .build();
}
