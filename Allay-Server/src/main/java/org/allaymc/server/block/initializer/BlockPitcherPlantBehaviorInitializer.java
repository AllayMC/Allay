package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPitcherPlantBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPitcherPlantBehaviorInitializer {
  static void init() {
    BlockTypes.PITCHER_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockPitcherPlantBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_PLANT)
            .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
