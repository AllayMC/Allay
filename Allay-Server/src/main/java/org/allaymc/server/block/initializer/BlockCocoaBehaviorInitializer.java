package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCocoaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCocoaBehaviorInitializer {
  static void init() {
    BlockTypes.COCOA_TYPE = BlockTypeBuilder
            .builder(BlockCocoaBehavior.class)
            .vanillaBlock(VanillaBlockId.COCOA)
            .setProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
