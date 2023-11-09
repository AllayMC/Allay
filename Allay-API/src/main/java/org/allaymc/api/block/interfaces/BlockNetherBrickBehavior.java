package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickBehavior extends BlockBehavior {
  BlockType<BlockNetherBrickBehavior> NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK)
          .build();
}
