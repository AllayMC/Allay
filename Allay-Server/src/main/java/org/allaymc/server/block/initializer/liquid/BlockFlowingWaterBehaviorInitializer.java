package org.allaymc.server.block.initializer.liquid;

import org.allaymc.api.block.interfaces.liquid.BlockFlowingWaterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFlowingWaterBehaviorInitializer {
  static void init() {
    BlockTypes.FLOWING_WATER_TYPE = BlockTypeBuilder
            .builder(BlockFlowingWaterBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWING_WATER)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }
}
