package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockPurpleWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleWoolBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockPurpleWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_WOOL)
            .build();
  }
}
