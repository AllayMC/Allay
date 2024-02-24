package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLadderBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLadderBehaviorInitializer {
  static void init() {
    BlockTypes.LADDER_TYPE = BlockTypeBuilder
            .builder(BlockLadderBehavior.class)
            .vanillaBlock(VanillaBlockId.LADDER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
