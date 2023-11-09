package org.allaymc.api.block.interfaces.log;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedBirchLogBehavior extends BlockBehavior {
  BlockType<BlockStrippedBirchLogBehavior> STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
