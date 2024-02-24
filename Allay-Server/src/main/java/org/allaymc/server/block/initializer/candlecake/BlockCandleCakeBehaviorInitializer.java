package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
