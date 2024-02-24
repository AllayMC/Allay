package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockCherryLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryLogBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_LOG_TYPE = BlockTypeBuilder
            .builder(BlockCherryLogBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
