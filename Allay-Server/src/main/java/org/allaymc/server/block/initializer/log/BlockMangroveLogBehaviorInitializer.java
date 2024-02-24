package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockMangroveLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveLogBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockMangroveLogBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
