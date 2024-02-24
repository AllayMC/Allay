package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFarmlandBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFarmlandBehaviorInitializer {
  static void init() {
    BlockTypes.FARMLAND_TYPE = BlockTypeBuilder
            .builder(BlockFarmlandBehavior.class)
            .vanillaBlock(VanillaBlockId.FARMLAND)
            .setProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
            .build();
  }
}
