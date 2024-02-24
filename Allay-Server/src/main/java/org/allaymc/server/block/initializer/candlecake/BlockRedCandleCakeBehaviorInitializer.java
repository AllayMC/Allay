package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockRedCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.RED_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockRedCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
