package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCactusBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCactusBehaviorInitializer {
  static void init() {
    BlockTypes.CACTUS_TYPE = BlockTypeBuilder
            .builder(BlockCactusBehavior.class)
            .vanillaBlock(VanillaBlockId.CACTUS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }
}
