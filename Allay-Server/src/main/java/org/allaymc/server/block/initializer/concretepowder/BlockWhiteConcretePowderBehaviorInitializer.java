package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockWhiteConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockWhiteConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CONCRETE_POWDER)
            .build();
  }
}
