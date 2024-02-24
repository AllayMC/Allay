package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEmeraldOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEmeraldOreBehaviorInitializer {
  static void init() {
    BlockTypes.EMERALD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockEmeraldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.EMERALD_ORE)
            .build();
  }
}
