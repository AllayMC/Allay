package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockYellowWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowWoolBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockYellowWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_WOOL)
            .build();
  }
}
