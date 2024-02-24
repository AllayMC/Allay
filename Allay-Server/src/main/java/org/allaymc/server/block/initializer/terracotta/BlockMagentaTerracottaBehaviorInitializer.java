package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockMagentaTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockMagentaTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_TERRACOTTA)
            .build();
  }
}
