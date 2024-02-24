package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedMangroveLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedMangroveLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_MANGROVE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedMangroveLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
