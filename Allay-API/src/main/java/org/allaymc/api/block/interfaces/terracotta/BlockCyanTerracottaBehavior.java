package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanTerracottaBehavior extends BlockBehavior {
  BlockType<BlockCyanTerracottaBehavior> CYAN_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockCyanTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_TERRACOTTA)
          .build();
}
