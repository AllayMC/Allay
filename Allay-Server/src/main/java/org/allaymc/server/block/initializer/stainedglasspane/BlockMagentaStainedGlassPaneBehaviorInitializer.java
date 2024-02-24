package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockMagentaStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_STAINED_GLASS_PANE)
            .build();
  }
}
