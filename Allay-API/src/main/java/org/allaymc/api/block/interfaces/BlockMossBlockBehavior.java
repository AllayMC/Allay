package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMossBlockBehavior extends BlockBehavior {
  BlockType<BlockMossBlockBehavior> MOSS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMossBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_BLOCK)
          .build();
}
