package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRailBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRailBehaviorInitializer {
  static void init() {
    BlockTypes.RAIL_TYPE = BlockTypeBuilder
            .builder(BlockRailBehavior.class)
            .vanillaBlock(VanillaBlockId.RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
            .build();
  }
}
