package org.allaymc.server.block.initializer.fencegate;

import org.allaymc.api.block.interfaces.fencegate.BlockBambooFenceGateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooFenceGateBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockBambooFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }
}
