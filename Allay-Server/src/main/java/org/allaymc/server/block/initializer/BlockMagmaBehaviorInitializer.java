package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMagmaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagmaBehaviorInitializer {
  static void init() {
    BlockTypes.MAGMA_TYPE = BlockTypeBuilder
            .builder(BlockMagmaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGMA)
            .build();
  }
}
