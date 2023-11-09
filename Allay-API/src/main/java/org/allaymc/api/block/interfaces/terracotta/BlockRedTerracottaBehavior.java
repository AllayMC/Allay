package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedTerracottaBehavior extends BlockBehavior {
  BlockType<BlockRedTerracottaBehavior> RED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockRedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_TERRACOTTA)
          .build();
}
