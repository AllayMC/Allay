package org.allaymc.api.block.interfaces.stairs;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTuffBrickStairsBehavior extends BlockBehavior {
  BlockType<BlockTuffBrickStairsBehavior> TUFF_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockTuffBrickStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.TUFF_BRICK_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .build();
}
