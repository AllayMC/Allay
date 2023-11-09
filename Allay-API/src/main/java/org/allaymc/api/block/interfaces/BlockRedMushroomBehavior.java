package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBehavior extends BlockBehavior {
  BlockType<BlockRedMushroomBehavior> RED_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM)
          .build();
}
