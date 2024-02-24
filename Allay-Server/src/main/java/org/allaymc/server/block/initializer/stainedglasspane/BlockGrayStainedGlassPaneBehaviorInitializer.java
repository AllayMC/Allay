package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockGrayStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGrayStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
