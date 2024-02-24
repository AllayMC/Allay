package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAzaleaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAzaleaBehaviorInitializer {
  static void init() {
    BlockTypes.AZALEA_TYPE = BlockTypeBuilder
            .builder(BlockAzaleaBehavior.class)
            .vanillaBlock(VanillaBlockId.AZALEA)
            .build();
  }
}
