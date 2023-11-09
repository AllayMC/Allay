package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownTerracottaBehavior extends BlockBehavior {
  BlockType<BlockBrownTerracottaBehavior> BROWN_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBrownTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_TERRACOTTA)
          .build();
}
