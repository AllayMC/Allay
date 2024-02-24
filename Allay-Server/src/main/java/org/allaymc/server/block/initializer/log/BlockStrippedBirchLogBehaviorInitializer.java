package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedBirchLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedBirchLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
