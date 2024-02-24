package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFlowerPotBehaviorInitializer {
  static void init() {
    BlockTypes.FLOWER_POT_TYPE = BlockTypeBuilder
            .builder(BlockFlowerPotBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWER_POT)
            .setProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
  }
}
