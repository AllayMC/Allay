package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockRedConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.RED_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockRedConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CONCRETE_POWDER)
            .build();
  }
}
