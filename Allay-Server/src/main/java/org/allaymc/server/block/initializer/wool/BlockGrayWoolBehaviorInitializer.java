package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockGrayWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayWoolBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockGrayWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_WOOL)
            .build();
  }
}
