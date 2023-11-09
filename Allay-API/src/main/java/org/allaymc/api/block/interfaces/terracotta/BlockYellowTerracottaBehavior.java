package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowTerracottaBehavior extends BlockBehavior {
  BlockType<BlockYellowTerracottaBehavior> YELLOW_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockYellowTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_TERRACOTTA)
          .build();
}
