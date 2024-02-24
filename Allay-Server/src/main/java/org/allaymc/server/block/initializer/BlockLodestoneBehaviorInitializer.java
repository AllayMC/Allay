package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLodestoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLodestoneBehaviorInitializer {
  static void init() {
    BlockTypes.LODESTONE_TYPE = BlockTypeBuilder
            .builder(BlockLodestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.LODESTONE)
            .build();
  }
}
