package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmoothStoneBehavior extends BlockBehavior {
  BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE_TYPE = BlockTypeBuilder
          .builder(BlockSmoothStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
          .build();
}
