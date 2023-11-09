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
public interface BlockMangroveLogBehavior extends BlockBehavior {
  BlockType<BlockMangroveLogBehavior> MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLogBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
