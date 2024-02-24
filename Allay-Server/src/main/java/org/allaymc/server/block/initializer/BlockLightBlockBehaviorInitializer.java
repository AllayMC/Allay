package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLightBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlockBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLightBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
            .build();
  }
}
