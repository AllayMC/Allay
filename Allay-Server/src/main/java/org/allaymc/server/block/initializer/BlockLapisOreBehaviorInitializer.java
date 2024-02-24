package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLapisOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLapisOreBehaviorInitializer {
  static void init() {
    BlockTypes.LAPIS_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLapisOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_ORE)
            .build();
  }
}
