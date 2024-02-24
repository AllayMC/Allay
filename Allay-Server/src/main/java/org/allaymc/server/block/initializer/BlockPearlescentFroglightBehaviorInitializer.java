package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPearlescentFroglightBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPearlescentFroglightBehaviorInitializer {
  static void init() {
    BlockTypes.PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockPearlescentFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
