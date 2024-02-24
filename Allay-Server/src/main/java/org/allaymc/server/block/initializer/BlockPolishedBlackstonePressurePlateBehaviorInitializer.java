package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedBlackstonePressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstonePressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
