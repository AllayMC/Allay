package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagentaTerracottaBehavior extends BlockBehavior {
  BlockType<BlockMagentaTerracottaBehavior> MAGENTA_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockMagentaTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_TERRACOTTA)
          .build();
}
