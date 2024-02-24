package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStonebrickBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStonebrickBehaviorInitializer {
  static void init() {
    BlockTypes.STONEBRICK_TYPE = BlockTypeBuilder
            .builder(BlockStonebrickBehavior.class)
            .vanillaBlock(VanillaBlockId.STONEBRICK)
            .setProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
            .build();
  }
}
