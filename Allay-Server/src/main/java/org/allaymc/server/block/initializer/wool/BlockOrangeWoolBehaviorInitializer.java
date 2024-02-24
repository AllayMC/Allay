package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockOrangeWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeWoolBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockOrangeWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_WOOL)
            .build();
  }
}
