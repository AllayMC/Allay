package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBeaconBehaviorInitializer {
  static void init() {
    BlockTypes.BEACON_TYPE = BlockTypeBuilder
            .builder(BlockBeaconBehavior.class)
            .vanillaBlock(VanillaBlockId.BEACON)
            .build();
  }
}
