package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockGrayConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockGrayConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CONCRETE_POWDER)
            .build();
  }
}
