package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardMagentaStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardMagentaStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardMagentaStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
            .build();
  }
}
