package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCaveVinesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCaveVinesBehaviorInitializer {
  static void init() {
    BlockTypes.CAVE_VINES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }
}
