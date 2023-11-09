package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBambooFenceBehavior extends BlockBehavior {
  BlockType<BlockBambooFenceBehavior> BAMBOO_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE)
          .build();
}
