package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedNetherBrickBehavior extends BlockBehavior {
  BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK)
          .build();
}
