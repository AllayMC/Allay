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
public interface BlockSeaPickleBehavior extends BlockBehavior {
  BlockType<BlockSeaPickleBehavior> SEA_PICKLE_TYPE = BlockTypeBuilder
          .builder(BlockSeaPickleBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_PICKLE)
          .setProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
          .build();
}
