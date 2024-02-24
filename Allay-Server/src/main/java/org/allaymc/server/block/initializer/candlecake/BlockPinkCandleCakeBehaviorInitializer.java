package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockPinkCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
