package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockBlueConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CONCRETE_POWDER)
            .build();
  }
}
