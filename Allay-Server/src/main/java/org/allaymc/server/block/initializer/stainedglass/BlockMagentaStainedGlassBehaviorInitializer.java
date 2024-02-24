package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockMagentaStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockMagentaStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_STAINED_GLASS)
            .build();
  }
}
