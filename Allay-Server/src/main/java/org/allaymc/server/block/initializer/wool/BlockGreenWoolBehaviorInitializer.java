package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockGreenWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenWoolBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockGreenWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_WOOL)
            .build();
  }
}
