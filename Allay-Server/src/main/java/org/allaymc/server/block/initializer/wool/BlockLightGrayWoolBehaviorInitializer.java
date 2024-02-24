package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockLightGrayWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayWoolBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL)
            .build();
  }
}
