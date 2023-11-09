package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedFungusBehavior extends BlockBehavior {
  BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_FUNGUS)
          .build();
}
