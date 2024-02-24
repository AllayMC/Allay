package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedBlackstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE)
            .build();
  }
}
