package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockBrownCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
