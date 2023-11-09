package org.allaymc.api.block.interfaces.bricks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedPolishedBlackstoneBricksBehavior extends BlockBehavior {
  BlockType<BlockCrackedPolishedBlackstoneBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
          .build();
}
