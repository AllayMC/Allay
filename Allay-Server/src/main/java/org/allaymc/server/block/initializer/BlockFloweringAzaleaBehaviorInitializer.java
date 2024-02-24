package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFloweringAzaleaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFloweringAzaleaBehaviorInitializer {
  static void init() {
    BlockTypes.FLOWERING_AZALEA_TYPE = BlockTypeBuilder
            .builder(BlockFloweringAzaleaBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA)
            .build();
  }
}
