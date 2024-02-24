package org.allaymc.server.block.initializer.glass;

import org.allaymc.api.block.interfaces.glass.BlockGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlassBehaviorInitializer {
  static void init() {
    BlockTypes.GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GLASS)
            .build();
  }
}
