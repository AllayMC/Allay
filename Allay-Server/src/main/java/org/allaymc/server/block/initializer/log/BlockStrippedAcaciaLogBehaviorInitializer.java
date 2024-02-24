package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockStrippedAcaciaLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStrippedAcaciaLogBehaviorInitializer {
  static void init() {
    BlockTypes.STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedAcaciaLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
