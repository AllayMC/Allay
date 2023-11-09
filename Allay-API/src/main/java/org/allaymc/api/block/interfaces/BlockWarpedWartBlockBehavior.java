package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedWartBlockBehavior extends BlockBehavior {
  BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK)
          .build();
}
