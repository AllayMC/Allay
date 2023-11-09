package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDragonEggBehavior extends BlockBehavior {
  BlockType<BlockDragonEggBehavior> DRAGON_EGG_TYPE = BlockTypeBuilder
          .builder(BlockDragonEggBehavior.class)
          .vanillaBlock(VanillaBlockId.DRAGON_EGG)
          .build();
}
