package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockBlackCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBlackCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
