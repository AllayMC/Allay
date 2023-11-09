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
public interface BlockStonebrickBehavior extends BlockBehavior {
  BlockType<BlockStonebrickBehavior> STONEBRICK_TYPE = BlockTypeBuilder
          .builder(BlockStonebrickBehavior.class)
          .vanillaBlock(VanillaBlockId.STONEBRICK)
          .setProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
          .build();
}
