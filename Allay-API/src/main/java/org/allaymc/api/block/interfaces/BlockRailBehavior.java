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
public interface BlockRailBehavior extends BlockBehavior {
  BlockType<BlockRailBehavior> RAIL_TYPE = BlockTypeBuilder
          .builder(BlockRailBehavior.class)
          .vanillaBlock(VanillaBlockId.RAIL)
          .setProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
          .build();
}
