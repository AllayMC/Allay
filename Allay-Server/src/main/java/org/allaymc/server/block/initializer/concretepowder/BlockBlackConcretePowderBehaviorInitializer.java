package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockBlackConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBlackConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CONCRETE_POWDER)
            .build();
  }
}
