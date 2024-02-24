package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockBrownConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBrownConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CONCRETE)
            .build();
  }
}
