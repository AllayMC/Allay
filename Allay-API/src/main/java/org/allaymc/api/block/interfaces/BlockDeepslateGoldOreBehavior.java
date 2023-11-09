package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateGoldOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
          .build();
}
