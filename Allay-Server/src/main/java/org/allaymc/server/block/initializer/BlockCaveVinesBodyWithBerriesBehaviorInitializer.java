package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCaveVinesBodyWithBerriesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCaveVinesBodyWithBerriesBehaviorInitializer {
  static void init() {
    BlockTypes.CAVE_VINES_BODY_WITH_BERRIES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }
}
