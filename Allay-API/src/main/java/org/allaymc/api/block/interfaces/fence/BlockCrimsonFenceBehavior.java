package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFenceBehavior extends BlockBehavior {
  BlockType<BlockCrimsonFenceBehavior> CRIMSON_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE)
          .build();
}
