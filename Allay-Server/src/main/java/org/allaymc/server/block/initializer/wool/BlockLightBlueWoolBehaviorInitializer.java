package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockLightBlueWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueWoolBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL)
            .build();
  }
}
