package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCherryFenceBehavior extends BlockBehavior {
  BlockType<BlockCherryFenceBehavior> CHERRY_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE)
          .build();
}
