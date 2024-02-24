package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLanternBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLanternBehaviorInitializer {
  static void init() {
    BlockTypes.LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.LANTERN)
            .setProperties(VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
