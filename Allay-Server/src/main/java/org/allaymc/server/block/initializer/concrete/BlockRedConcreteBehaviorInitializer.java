package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockRedConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.RED_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockRedConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CONCRETE)
            .build();
  }
}
