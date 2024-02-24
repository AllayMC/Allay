package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSnowLayerBehaviorInitializer {
  static void init() {
    BlockTypes.SNOW_LAYER_TYPE = BlockTypeBuilder
            .builder(BlockSnowLayerBehavior.class)
            .vanillaBlock(VanillaBlockId.SNOW_LAYER)
            .setProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
            .build();
  }
}
