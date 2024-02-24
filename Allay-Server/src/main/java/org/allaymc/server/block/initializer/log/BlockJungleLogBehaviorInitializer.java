package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockJungleLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJungleLogBehaviorInitializer {
  static void init() {
    BlockTypes.JUNGLE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockJungleLogBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
