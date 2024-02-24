package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockAcaciaLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaLogBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_LOG_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaLogBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
