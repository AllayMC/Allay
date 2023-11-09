package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

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
