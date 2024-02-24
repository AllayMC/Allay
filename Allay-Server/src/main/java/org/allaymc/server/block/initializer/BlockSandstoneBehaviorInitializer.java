package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSandstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSandstoneBehaviorInitializer {
  static void init() {
    BlockTypes.SANDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockSandstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE)
            .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .build();
  }
}
