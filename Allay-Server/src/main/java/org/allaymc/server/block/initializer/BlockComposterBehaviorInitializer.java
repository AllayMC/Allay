package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockComposterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockComposterBehaviorInitializer {
  static void init() {
    BlockTypes.COMPOSTER_TYPE = BlockTypeBuilder
            .builder(BlockComposterBehavior.class)
            .vanillaBlock(VanillaBlockId.COMPOSTER)
            .setProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
            .build();
  }
}
