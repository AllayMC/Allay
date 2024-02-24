package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockGrayConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockGrayConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CONCRETE)
            .build();
  }
}
