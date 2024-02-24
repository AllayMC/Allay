package org.allaymc.server.block.initializer.wallsign;

import org.allaymc.api.block.interfaces.wallsign.BlockAcaciaWallSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaWallSignBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
