package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockBrownConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBrownConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CONCRETE_POWDER)
            .build();
  }
}
