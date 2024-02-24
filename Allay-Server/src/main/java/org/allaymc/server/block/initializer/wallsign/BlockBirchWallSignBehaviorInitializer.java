package org.allaymc.server.block.initializer.wallsign;

import org.allaymc.api.block.interfaces.wallsign.BlockBirchWallSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchWallSignBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_WALL_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchWallSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_WALL_SIGN)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
