package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockIronBarsBehavior extends BlockBehavior {
  BlockType<BlockIronBarsBehavior> IRON_BARS_TYPE = BlockTypeBuilder
          .builder(BlockIronBarsBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BARS)
          .build();
}
