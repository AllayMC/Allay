package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockUnknownBehavior extends BlockBehavior {
  BlockType<BlockUnknownBehavior> UNKNOWN_TYPE = BlockTypeBuilder
          .builder(BlockUnknownBehavior.class)
          .vanillaBlock(VanillaBlockId.UNKNOWN)
          .build();
}
