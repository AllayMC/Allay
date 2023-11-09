package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakFenceBehavior extends BlockBehavior {
  BlockType<BlockDarkOakFenceBehavior> DARK_OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE)
          .build();
}
