package org.allaymc.server.block.initializer.trapdoor;

import org.allaymc.api.block.interfaces.trapdoor.BlockWarpedTrapdoorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedTrapdoorBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockWarpedTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
  }
}
