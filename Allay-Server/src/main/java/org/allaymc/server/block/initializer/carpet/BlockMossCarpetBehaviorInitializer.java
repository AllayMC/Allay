package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockMossCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMossCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.MOSS_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockMossCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSS_CARPET)
            .build();
  }
}
