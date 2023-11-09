package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBricksBehavior extends BlockBehavior {
  BlockType<BlockDeepslateBricksBehavior> DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS)
          .build();
}
