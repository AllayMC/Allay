package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockPurpleConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE)
            .build();
  }
}
