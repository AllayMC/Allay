package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockBlueConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE)
            .build();
  }
}
