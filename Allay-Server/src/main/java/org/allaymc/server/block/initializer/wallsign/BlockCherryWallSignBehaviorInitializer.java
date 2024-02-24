package org.allaymc.server.block.initializer.wallsign;

import org.allaymc.api.block.interfaces.wallsign.BlockCherryWallSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryWallSignBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCherryWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
