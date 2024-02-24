package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDirtBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDirtBehaviorInitializer {
  static void init() {
    BlockTypes.DIRT_TYPE = BlockTypeBuilder
            .builder(BlockDirtBehavior.class)
            .vanillaBlock(VanillaBlockId.DIRT)
            .setProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
            .build();
  }
}
