package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockBrownWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownWoolBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBrownWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_WOOL)
            .build();
  }
}
