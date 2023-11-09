package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayTerracottaBehavior extends BlockBehavior {
  BlockType<BlockLightGrayTerracottaBehavior> LIGHT_GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_TERRACOTTA)
          .build();
}
