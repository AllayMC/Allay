package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockBlueWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueWoolBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_WOOL)
            .build();
  }
}
