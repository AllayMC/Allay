package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedstoneWireBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneWireBehaviorInitializer {
  static void init() {
    BlockTypes.REDSTONE_WIRE_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneWireBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_WIRE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
