package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWitherRoseBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWitherRoseBehaviorInitializer {
  static void init() {
    BlockTypes.WITHER_ROSE_TYPE = BlockTypeBuilder
            .builder(BlockWitherRoseBehavior.class)
            .vanillaBlock(VanillaBlockId.WITHER_ROSE)
            .build();
  }
}
