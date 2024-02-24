package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedBasaltBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBasaltBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BASALT_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BASALT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
