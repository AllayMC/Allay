package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockOrangeConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockOrangeConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE_POWDER)
            .build();
  }
}
