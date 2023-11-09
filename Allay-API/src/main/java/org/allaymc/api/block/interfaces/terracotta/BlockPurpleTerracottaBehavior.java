package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleTerracottaBehavior extends BlockBehavior {
  BlockType<BlockPurpleTerracottaBehavior> PURPLE_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPurpleTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_TERRACOTTA)
          .build();
}
