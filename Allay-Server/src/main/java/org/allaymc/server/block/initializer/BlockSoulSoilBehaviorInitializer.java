package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSoulSoilBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulSoilBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_SOIL_TYPE = BlockTypeBuilder
            .builder(BlockSoulSoilBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_SOIL)
            .build();
  }
}
