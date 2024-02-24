package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockGreenConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE_POWDER)
            .build();
  }
}
