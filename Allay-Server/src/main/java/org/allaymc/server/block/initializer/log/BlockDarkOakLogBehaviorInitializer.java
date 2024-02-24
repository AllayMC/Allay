package org.allaymc.server.block.initializer.log;

import org.allaymc.api.block.interfaces.log.BlockDarkOakLogBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakLogBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
