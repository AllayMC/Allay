package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBlackstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackstoneBehaviorInitializer {
  static void init() {
    BlockTypes.BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE)
            .build();
  }
}
