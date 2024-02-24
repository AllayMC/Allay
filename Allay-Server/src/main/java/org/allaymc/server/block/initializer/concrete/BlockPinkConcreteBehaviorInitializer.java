package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockPinkConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockPinkConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CONCRETE)
            .build();
  }
}
