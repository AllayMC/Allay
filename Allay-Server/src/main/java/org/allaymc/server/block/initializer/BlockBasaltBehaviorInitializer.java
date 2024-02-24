package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBasaltBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBasaltBehaviorInitializer {
  static void init() {
    BlockTypes.BASALT_TYPE = BlockTypeBuilder
            .builder(BlockBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.BASALT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
