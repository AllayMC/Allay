package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockClientRequestPlaceholderBlockBehavior extends BlockBehavior {
  BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockClientRequestPlaceholderBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
          .build();
}
