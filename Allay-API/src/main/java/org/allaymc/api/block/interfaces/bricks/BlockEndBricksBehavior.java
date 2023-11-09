package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEndBricksBehavior extends BlockBehavior {
  BlockType<BlockEndBricksBehavior> END_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockEndBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.END_BRICKS)
          .build();
}
