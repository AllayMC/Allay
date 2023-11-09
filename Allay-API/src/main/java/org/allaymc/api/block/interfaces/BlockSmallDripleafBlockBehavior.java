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
public interface BlockSmallDripleafBlockBehavior extends BlockBehavior {
  BlockType<BlockSmallDripleafBlockBehavior> SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockSmallDripleafBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
