package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDirtWithRootsBehavior extends BlockBehavior {
  BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockDirtWithRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS)
          .build();
}
