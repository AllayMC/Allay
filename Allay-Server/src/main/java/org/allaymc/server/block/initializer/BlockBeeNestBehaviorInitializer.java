package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBeeNestBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBeeNestBehaviorInitializer {
  static void init() {
    BlockTypes.BEE_NEST_TYPE = BlockTypeBuilder
            .builder(BlockBeeNestBehavior.class)
            .vanillaBlock(VanillaBlockId.BEE_NEST)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
            .build();
  }
}
