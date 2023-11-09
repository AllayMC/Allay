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
public interface BlockJungleLogBehavior extends BlockBehavior {
  BlockType<BlockJungleLogBehavior> JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockJungleLogBehavior.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
