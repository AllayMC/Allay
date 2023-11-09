package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherWartBlockBehavior extends BlockBehavior {
  BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
          .build();
}
