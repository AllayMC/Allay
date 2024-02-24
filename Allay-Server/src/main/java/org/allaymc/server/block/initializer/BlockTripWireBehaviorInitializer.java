package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTripWireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTripWireBehaviorInitializer {
  static void init() {
    BlockTypes.TRIP_WIRE_TYPE = BlockTypeBuilder
            .builder(BlockTripWireBehavior.class)
            .vanillaBlock(VanillaBlockId.TRIP_WIRE)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT)
            .build();
  }
}
