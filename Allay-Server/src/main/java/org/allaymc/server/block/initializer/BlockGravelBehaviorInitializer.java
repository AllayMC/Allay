package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGravelBehaviorInitializer {
  static void init() {
    BlockTypes.GRAVEL_TYPE = BlockTypeBuilder
            .builder(BlockGravelBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAVEL)
            .build();
  }
}
