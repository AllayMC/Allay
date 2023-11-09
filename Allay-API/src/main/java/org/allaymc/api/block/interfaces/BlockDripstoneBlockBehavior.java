package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDripstoneBlockBehavior extends BlockBehavior {
  BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDripstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK)
          .build();
}
