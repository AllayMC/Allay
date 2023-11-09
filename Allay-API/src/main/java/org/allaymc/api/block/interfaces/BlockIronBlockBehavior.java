package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockIronBlockBehavior extends BlockBehavior {
  BlockType<BlockIronBlockBehavior> IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BLOCK)
          .build();
}
