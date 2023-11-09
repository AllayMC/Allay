package org.allaymc.api.block.interfaces.terracotta;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueTerracottaBehavior extends BlockBehavior {
  BlockType<BlockLightBlueTerracottaBehavior> LIGHT_BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_TERRACOTTA)
          .build();
}
