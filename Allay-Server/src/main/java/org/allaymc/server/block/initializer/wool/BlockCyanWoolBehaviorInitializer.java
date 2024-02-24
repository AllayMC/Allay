package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockCyanWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanWoolBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockCyanWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_WOOL)
            .build();
  }
}
