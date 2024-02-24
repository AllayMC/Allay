package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGrayStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
