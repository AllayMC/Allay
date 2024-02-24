package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockBirchLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchLogBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
