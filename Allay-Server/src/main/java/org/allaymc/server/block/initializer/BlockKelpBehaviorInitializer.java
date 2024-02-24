package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockKelpBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockKelpBehaviorInitializer {
  static void init() {
    BlockTypes.KELP_TYPE = BlockTypeBuilder
            .builder(BlockKelpBehavior.class)
            .vanillaBlock(VanillaBlockId.KELP)
            .setProperties(VanillaBlockPropertyTypes.KELP_AGE)
            .build();
  }
}
