package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedJungleLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedJungleLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedJungleLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
