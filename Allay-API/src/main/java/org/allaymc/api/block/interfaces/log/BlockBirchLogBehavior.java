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
public interface BlockBirchLogBehavior extends BlockBehavior {
  BlockType<BlockBirchLogBehavior> BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockBirchLogBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
