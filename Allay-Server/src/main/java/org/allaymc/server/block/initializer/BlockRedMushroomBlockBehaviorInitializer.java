package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedMushroomBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBlockBehaviorInitializer {
  static void init() {
    BlockTypes.RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .build();
  }
}
