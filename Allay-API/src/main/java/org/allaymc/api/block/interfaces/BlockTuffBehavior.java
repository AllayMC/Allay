package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTuffBehavior extends BlockBehavior {
  BlockType<BlockTuffBehavior> TUFF_TYPE = BlockTypeBuilder
          .builder(BlockTuffBehavior.class)
          .vanillaBlock(VanillaBlockId.TUFF)
          .build();
}
