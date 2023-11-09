package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSporeBlossomBehavior extends BlockBehavior {
  BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM_TYPE = BlockTypeBuilder
          .builder(BlockSporeBlossomBehavior.class)
          .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
          .build();
}
