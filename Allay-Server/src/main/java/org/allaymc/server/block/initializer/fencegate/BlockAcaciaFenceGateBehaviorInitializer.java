package org.allaymc.server.block.initializer.fencegate;

import org.allaymc.api.block.interfaces.fencegate.BlockAcaciaFenceGateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaFenceGateBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
  }
}
