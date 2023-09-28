package cn.allay.api.block.interfaces.bricks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockQuartzBricksBehavior extends BlockBehavior {
  BlockType<BlockQuartzBricksBehavior> QUARTZ_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS)
          .build();
}
