package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAcaciaFenceBehavior extends BlockBehavior {
  BlockType<BlockAcaciaFenceBehavior> ACACIA_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE)
          .build();
}
