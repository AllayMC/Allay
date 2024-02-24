package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBambooMosaicBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooMosaicBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_MOSAIC_TYPE = BlockTypeBuilder
            .builder(BlockBambooMosaicBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC)
            .build();
  }
}
