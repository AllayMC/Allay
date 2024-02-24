package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFrostedIceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFrostedIceBehaviorInitializer {
  static void init() {
    BlockTypes.FROSTED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockFrostedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.FROSTED_ICE)
            .setProperties(VanillaBlockPropertyTypes.AGE_4)
            .build();
  }
}
