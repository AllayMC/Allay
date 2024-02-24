package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSculkBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkBehaviorInitializer {
  static void init() {
    BlockTypes.SCULK_TYPE = BlockTypeBuilder
            .builder(BlockSculkBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK)
            .build();
  }
}
