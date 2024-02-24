package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockBlackWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackWoolBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockBlackWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_WOOL)
            .build();
  }
}
