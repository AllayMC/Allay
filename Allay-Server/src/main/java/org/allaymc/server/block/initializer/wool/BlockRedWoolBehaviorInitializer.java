package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockRedWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedWoolBehaviorInitializer {
  static void init() {
    BlockTypes.RED_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockRedWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_WOOL)
            .build();
  }
}
