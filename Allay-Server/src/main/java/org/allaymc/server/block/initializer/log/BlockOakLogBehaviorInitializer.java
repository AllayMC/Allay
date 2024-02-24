package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockOakLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOakLogBehaviorInitializer {
  static void init() {
    BlockTypes.OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
