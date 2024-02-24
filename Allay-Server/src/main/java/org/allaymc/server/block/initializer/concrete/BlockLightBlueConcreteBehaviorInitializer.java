package org.allaymc.server.block.initializer.concrete;

import org.allaymc.api.block.interfaces.concrete.BlockLightBlueConcreteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueConcreteBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_CONCRETE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueConcreteBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE)
            .build();
  }
}
