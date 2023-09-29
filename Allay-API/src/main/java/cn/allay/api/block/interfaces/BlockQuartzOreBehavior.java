package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockQuartzOreBehavior extends BlockBehavior {
  BlockType<BlockQuartzOreBehavior> QUARTZ_ORE_TYPE = BlockTypeBuilder
          .builder(BlockQuartzOreBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_ORE)
          .build();
}
