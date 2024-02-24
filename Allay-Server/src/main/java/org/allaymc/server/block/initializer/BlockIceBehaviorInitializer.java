package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockIceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIceBehaviorInitializer {
  static void init() {
    BlockTypes.ICE_TYPE = BlockTypeBuilder
            .builder(BlockIceBehavior.class)
            .vanillaBlock(VanillaBlockId.ICE)
            .build();
  }
}
