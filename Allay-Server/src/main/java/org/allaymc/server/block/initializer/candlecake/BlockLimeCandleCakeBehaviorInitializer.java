package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockLimeCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLimeCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
