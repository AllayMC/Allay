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
public interface BlockStoneBehavior extends BlockBehavior {
  BlockType<BlockStoneBehavior> STONE_TYPE = BlockTypeBuilder
          .builder(BlockStoneBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE)
          .setProperties(VanillaBlockPropertyTypes.STONE_TYPE)
          .build();
}
