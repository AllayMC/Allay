package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedDioriteBehavior extends BlockBehavior {
  BlockType<BlockPolishedDioriteBehavior> POLISHED_DIORITE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDioriteBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DIORITE)
          .build();
}
