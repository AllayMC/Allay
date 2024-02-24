package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockGreenConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockGreenConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CONCRETE)
            .build();
  }
}
