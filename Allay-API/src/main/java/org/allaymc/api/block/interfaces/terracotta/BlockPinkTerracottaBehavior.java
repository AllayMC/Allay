package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkTerracottaBehavior extends BlockBehavior {
  BlockType<BlockPinkTerracottaBehavior> PINK_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPinkTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_TERRACOTTA)
          .build();
}
