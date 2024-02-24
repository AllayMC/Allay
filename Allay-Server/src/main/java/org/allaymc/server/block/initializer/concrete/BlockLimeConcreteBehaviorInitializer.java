package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockLimeConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLimeConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CONCRETE)
            .build();
  }
}
