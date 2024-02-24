package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockOrangeCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
