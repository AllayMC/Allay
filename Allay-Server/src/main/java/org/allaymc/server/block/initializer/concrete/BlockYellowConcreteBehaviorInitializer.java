package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockYellowConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockYellowConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE)
            .build();
  }
}
