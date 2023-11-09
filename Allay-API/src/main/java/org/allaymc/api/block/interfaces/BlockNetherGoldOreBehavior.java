package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherGoldOreBehavior extends BlockBehavior {
  BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockNetherGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE)
          .build();
}
