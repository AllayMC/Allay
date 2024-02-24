package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCaveVinesHeadWithBerriesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCaveVinesHeadWithBerriesBehaviorInitializer {
  static void init() {
    BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
  }
}
