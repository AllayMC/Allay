package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBlockBehavior extends BlockBehavior {
  BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .build();
}
