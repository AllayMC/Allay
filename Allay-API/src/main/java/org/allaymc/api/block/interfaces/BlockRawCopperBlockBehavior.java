package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRawCopperBlockBehavior extends BlockBehavior {
  BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK)
          .build();
}
