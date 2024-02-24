package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockPinkWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkWoolBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockPinkWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_WOOL)
            .build();
  }
}
