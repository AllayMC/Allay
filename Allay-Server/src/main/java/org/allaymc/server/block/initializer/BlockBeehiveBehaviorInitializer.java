package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBeehiveBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBeehiveBehaviorInitializer {
  static void init() {
    BlockTypes.BEEHIVE_TYPE = BlockTypeBuilder
            .builder(BlockBeehiveBehavior.class)
            .vanillaBlock(VanillaBlockId.BEEHIVE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
            .build();
  }
}
