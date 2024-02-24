package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGildedBlackstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGildedBlackstoneBehaviorInitializer {
  static void init() {
    BlockTypes.GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGildedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE)
            .build();
  }
}
