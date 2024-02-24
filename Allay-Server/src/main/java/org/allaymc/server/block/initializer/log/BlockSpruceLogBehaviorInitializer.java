package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockSpruceLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSpruceLogBehaviorInitializer {
  static void init() {
    BlockTypes.SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
