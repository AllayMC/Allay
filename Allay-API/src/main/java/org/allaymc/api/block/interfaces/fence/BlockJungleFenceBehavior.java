package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJungleFenceBehavior extends BlockBehavior {
  BlockType<BlockJungleFenceBehavior> JUNGLE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE)
          .build();
}
