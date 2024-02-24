package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateEmeraldOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateEmeraldOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateEmeraldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE)
            .build();
  }
}
