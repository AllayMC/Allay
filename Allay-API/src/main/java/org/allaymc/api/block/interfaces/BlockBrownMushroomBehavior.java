package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownMushroomBehavior extends BlockBehavior {
  BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM)
          .build();
}
