package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockLightGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
