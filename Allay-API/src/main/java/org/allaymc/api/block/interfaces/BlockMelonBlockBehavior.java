package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMelonBlockBehavior extends BlockBehavior {
  BlockType<BlockMelonBlockBehavior> MELON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMelonBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.MELON_BLOCK)
          .build();
}
