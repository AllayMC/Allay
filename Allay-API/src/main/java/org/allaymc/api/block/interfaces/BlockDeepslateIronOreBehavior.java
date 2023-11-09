package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateIronOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateIronOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE)
          .build();
}
