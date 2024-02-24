package org.allaymc.server.block.initializer.liquid;

import org.allaymc.api.block.interfaces.liquid.BlockWaterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaterBehaviorInitializer {
  static void init() {
    BlockTypes.WATER_TYPE = BlockTypeBuilder
            .builder(BlockWaterBehavior.class)
            .vanillaBlock(VanillaBlockId.WATER)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }
}
