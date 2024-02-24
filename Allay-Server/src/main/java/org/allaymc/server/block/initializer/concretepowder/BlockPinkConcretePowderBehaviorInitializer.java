package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockPinkConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockPinkConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CONCRETE_POWDER)
            .build();
  }
}
