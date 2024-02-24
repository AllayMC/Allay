package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockCrackedDeepslateBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateBricksBehaviorInitializer {
  static void init() {
    BlockTypes.CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS)
            .build();
  }
}
