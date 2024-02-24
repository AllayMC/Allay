package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPotatoesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPotatoesBehaviorInitializer {
  static void init() {
    BlockTypes.POTATOES_TYPE = BlockTypeBuilder
            .builder(BlockPotatoesBehavior.class)
            .vanillaBlock(VanillaBlockId.POTATOES)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
