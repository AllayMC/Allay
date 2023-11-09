package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickFenceBehavior extends BlockBehavior {
  BlockType<BlockNetherBrickFenceBehavior> NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE)
          .build();
}
