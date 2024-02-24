package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockGrayStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGrayStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS)
            .build();
  }
}
