package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockVineBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockVineBehaviorInitializer {
  static void init() {
    BlockTypes.VINE_TYPE = BlockTypeBuilder
            .builder(BlockVineBehavior.class)
            .vanillaBlock(VanillaBlockId.VINE)
            .setProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
            .build();
  }
}
