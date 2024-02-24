package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTrappedChestBehaviorInitializer {
  static void init() {
    BlockTypes.TRAPPED_CHEST_TYPE = BlockTypeBuilder
            .builder(BlockTrappedChestBehavior.class)
            .vanillaBlock(VanillaBlockId.TRAPPED_CHEST)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
