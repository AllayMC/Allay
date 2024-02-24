package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedFlowerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedFlowerBehaviorInitializer {
  static void init() {
    BlockTypes.RED_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockRedFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_FLOWER)
            .setProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
            .build();
  }
}
