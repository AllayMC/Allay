package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLeverBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLeverBehaviorInitializer {
  static void init() {
    BlockTypes.LEVER_TYPE = BlockTypeBuilder
            .builder(BlockLeverBehavior.class)
            .vanillaBlock(VanillaBlockId.LEVER)
            .setProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }
}
