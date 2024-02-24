package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockWhiteCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
