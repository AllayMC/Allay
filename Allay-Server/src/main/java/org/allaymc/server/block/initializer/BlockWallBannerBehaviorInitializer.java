package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWallBannerBehaviorInitializer {
  static void init() {
    BlockTypes.WALL_BANNER_TYPE = BlockTypeBuilder
            .builder(BlockWallBannerBehavior.class)
            .vanillaBlock(VanillaBlockId.WALL_BANNER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
