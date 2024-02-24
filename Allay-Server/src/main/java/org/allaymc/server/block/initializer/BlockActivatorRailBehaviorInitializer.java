package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockActivatorRailBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockActivatorRailBehaviorInitializer {
  static void init() {
    BlockTypes.ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockActivatorRailBehavior.class)
            .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }
}
