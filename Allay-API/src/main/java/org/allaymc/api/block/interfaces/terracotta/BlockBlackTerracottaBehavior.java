package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackTerracottaBehavior extends BlockBehavior {
  BlockType<BlockBlackTerracottaBehavior> BLACK_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlackTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BLACK_TERRACOTTA)
          .build();
}
