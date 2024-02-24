package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherWartBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherWartBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_WART_TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART)
            .setProperties(VanillaBlockPropertyTypes.AGE_4)
            .build();
  }
}
