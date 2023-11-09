package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedNetherBricksBehavior extends BlockBehavior {
  BlockType<BlockCrackedNetherBricksBehavior> CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedNetherBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS)
          .build();
}
