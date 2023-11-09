package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOakFenceBehavior extends BlockBehavior {
  BlockType<BlockOakFenceBehavior> OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockOakFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.OAK_FENCE)
          .build();
}
