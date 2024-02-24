package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockCyanConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockCyanConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CONCRETE)
            .build();
  }
}
