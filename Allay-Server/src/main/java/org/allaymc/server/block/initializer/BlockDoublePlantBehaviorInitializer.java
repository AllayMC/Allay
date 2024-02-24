package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDoublePlantBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoublePlantBehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockDoublePlantBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
            .setProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
  }
}
