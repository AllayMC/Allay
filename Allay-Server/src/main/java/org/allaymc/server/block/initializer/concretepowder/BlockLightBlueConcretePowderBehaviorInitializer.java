package org.allaymc.server.block.initializer.concretepowder;

import org.allaymc.api.block.interfaces.concretepowder.BlockLightBlueConcretePowderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueConcretePowderBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_CONCRETE_POWDER_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueConcretePowderBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE_POWDER)
            .build();
  }
}
