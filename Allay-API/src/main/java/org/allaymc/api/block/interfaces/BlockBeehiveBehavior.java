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
public interface BlockBeehiveBehavior extends BlockBehavior {
  BlockType<BlockBeehiveBehavior> BEEHIVE_TYPE = BlockTypeBuilder
          .builder(BlockBeehiveBehavior.class)
          .vanillaBlock(VanillaBlockId.BEEHIVE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .build();
}
