package org.allaymc.server.block.initializer.wallsign;

import org.allaymc.api.block.interfaces.wallsign.BlockWarpedWallSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedWallSignBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWarpedWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
