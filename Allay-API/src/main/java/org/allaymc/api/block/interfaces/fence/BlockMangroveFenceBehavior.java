package org.allaymc.api.block.interfaces.fence;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveFenceBehavior extends BlockBehavior {
  BlockType<BlockMangroveFenceBehavior> MANGROVE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE)
          .build();
}
