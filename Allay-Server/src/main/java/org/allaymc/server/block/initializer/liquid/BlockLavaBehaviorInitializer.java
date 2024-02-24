package org.allaymc.server.block.initializer.liquid;

import org.allaymc.api.block.interfaces.liquid.BlockLavaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLavaBehaviorInitializer {
  static void init() {
    BlockTypes.LAVA_TYPE = BlockTypeBuilder
            .builder(BlockLavaBehavior.class)
            .vanillaBlock(VanillaBlockId.LAVA)
            .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .build();
  }
}
