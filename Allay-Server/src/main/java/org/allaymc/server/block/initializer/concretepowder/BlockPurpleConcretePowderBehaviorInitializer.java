package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockPurpleConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockPurpleConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CONCRETE_POWDER)
            .build();
  }
}
