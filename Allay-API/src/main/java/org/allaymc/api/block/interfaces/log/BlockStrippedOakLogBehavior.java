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
public interface BlockStrippedOakLogBehavior extends BlockBehavior {
  BlockType<BlockStrippedOakLogBehavior> STRIPPED_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedOakLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_OAK_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
