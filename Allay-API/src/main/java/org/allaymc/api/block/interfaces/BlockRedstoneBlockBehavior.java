package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedstoneBlockBehavior extends BlockBehavior {
  BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK)
          .build();
}
