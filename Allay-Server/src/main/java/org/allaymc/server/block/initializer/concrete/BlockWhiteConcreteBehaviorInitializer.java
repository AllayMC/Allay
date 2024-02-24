package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockWhiteConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CONCRETE)
            .build();
  }
}
