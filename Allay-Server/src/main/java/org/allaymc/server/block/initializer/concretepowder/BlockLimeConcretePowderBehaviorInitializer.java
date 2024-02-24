package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockLimeConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLimeConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CONCRETE_POWDER)
            .build();
  }
}
