package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowFlowerBehavior extends BlockBehavior {
  BlockType<BlockYellowFlowerBehavior> YELLOW_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockYellowFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_FLOWER)
          .build();
}
