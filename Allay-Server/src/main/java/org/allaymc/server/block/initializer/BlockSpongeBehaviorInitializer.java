package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSpongeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSpongeBehaviorInitializer {
  static void init() {
    BlockTypes.SPONGE_TYPE = BlockTypeBuilder
            .builder(BlockSpongeBehavior.class)
            .vanillaBlock(VanillaBlockId.SPONGE)
            .setProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
            .build();
  }
}
