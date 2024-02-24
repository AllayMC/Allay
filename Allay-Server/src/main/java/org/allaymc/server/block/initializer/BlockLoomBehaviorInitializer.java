package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLoomBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLoomBehaviorInitializer {
  static void init() {
    BlockTypes.LOOM_TYPE = BlockTypeBuilder
            .builder(BlockLoomBehavior.class)
            .vanillaBlock(VanillaBlockId.LOOM)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
