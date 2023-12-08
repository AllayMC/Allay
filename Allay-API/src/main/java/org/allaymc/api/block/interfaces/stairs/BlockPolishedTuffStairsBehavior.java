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
public interface BlockPolishedTuffStairsBehavior extends BlockBehavior {
  BlockType<BlockPolishedTuffStairsBehavior> POLISHED_TUFF_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedTuffStairsBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_TUFF_STAIRS)
          .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .build();
}
