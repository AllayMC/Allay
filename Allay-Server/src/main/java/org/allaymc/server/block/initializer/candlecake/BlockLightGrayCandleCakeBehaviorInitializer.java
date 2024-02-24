package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockLightGrayCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
