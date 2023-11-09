package org.allaymc.api.block.interfaces.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedTrapdoorBehavior extends BlockBehavior {
  BlockType<BlockWarpedTrapdoorBehavior> WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedTrapdoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
          .build();
}
