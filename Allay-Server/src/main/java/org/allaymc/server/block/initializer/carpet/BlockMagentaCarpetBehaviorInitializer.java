package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockMagentaCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CARPET)
            .build();
  }
}
