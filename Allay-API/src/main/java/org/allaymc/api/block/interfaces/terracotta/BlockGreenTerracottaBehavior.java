package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenTerracottaBehavior extends BlockBehavior {
  BlockType<BlockGreenTerracottaBehavior> GREEN_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGreenTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_TERRACOTTA)
          .build();
}
