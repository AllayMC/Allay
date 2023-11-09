package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSoulSandBehavior extends BlockBehavior {
  BlockType<BlockSoulSandBehavior> SOUL_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSoulSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SAND)
          .build();
}
