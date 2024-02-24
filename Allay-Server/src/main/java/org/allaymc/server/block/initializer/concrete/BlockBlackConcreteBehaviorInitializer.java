package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockBlackConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlackConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CONCRETE)
            .build();
  }
}
