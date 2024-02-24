package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedSandstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedSandstoneBehaviorInitializer {
  static void init() {
    BlockTypes.RED_SANDSTONE_TYPE = BlockTypeBuilder
            .builder(BlockRedSandstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_SANDSTONE)
            .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .build();
  }
}
