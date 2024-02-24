package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockOrangeConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CONCRETE)
            .build();
  }
}
