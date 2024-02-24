package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChorusPlantBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChorusPlantBehaviorInitializer {
  static void init() {
    BlockTypes.CHORUS_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockChorusPlantBehavior.class)
            .vanillaBlock(VanillaBlockId.CHORUS_PLANT)
            .build();
  }
}
