package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardMagentaStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardMagentaStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_MAGENTA_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardMagentaStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_MAGENTA_STAINED_GLASS)
            .build();
  }
}
