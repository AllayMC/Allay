package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockLightGrayConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE_POWDER)
            .build();
  }
}
