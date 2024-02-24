package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockLightGrayStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_STAINED_GLASS)
            .build();
  }
}
