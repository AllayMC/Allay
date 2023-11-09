package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateBricksBehavior extends BlockBehavior {
  BlockType<BlockCrackedDeepslateBricksBehavior> CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS)
          .build();
}
