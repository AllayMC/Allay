package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockLightGrayConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CONCRETE)
            .build();
  }
}
