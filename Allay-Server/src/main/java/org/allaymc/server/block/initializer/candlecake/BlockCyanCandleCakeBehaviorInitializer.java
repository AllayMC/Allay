package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockCyanCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCyanCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
