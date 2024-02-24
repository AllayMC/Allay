package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChiseledPolishedBlackstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledPolishedBlackstoneBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockChiseledPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE)
            .build();
  }
}
