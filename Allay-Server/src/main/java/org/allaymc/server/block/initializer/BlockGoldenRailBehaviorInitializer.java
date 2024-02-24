package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGoldenRailBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGoldenRailBehaviorInitializer {
  static void init() {
    BlockTypes.GOLDEN_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockGoldenRailBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLDEN_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }
}
