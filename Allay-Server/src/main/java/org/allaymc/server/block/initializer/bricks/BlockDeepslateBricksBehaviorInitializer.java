package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockDeepslateBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBricksBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS)
            .build();
  }
}
