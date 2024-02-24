package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockGrayCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockGrayCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
