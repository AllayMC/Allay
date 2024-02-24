package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedSpruceLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedSpruceLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
