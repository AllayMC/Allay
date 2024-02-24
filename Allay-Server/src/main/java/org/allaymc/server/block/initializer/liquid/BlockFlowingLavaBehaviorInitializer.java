package org.allaymc.server.block.initializer.liquid;

import org.allaymc.api.block.interfaces.liquid.BlockFlowingLavaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFlowingLavaBehaviorInitializer {
  static void init() {
    BlockTypes.FLOWING_LAVA_TYPE = BlockTypeBuilder
            .builder(BlockFlowingLavaBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWING_LAVA)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }
}
