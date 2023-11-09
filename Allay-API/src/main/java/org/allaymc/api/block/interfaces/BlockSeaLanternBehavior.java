package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSeaLanternBehavior extends BlockBehavior {
  BlockType<BlockSeaLanternBehavior> SEA_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSeaLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_LANTERN)
          .build();
}
