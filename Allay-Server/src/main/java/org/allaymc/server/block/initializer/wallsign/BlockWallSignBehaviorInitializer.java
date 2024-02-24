package org.allaymc.server.block.initializer.wallsign;

import org.allaymc.api.block.interfaces.wallsign.BlockWallSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWallSignBehaviorInitializer {
  static void init() {
    BlockTypes.WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
