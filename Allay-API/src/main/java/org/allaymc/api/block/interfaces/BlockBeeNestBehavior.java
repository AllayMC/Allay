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
public interface BlockBeeNestBehavior extends BlockBehavior {
  BlockType<BlockBeeNestBehavior> BEE_NEST_TYPE = BlockTypeBuilder
          .builder(BlockBeeNestBehavior.class)
          .vanillaBlock(VanillaBlockId.BEE_NEST)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .build();
}
