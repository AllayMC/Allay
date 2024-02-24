package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardLightGrayStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardLightGrayStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLightGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
            .build();
  }
}
