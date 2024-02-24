package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockWhiteWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteWoolBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockWhiteWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_WOOL)
            .build();
  }
}
