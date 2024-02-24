package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardLightGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardLightGrayStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardLightGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
