package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTuffBricksBehavior extends BlockBehavior {
  BlockType<BlockTuffBricksBehavior> TUFF_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockTuffBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.TUFF_BRICKS)
          .build();
}
