package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockYellowConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockYellowConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE_POWDER)
            .build();
  }
}
