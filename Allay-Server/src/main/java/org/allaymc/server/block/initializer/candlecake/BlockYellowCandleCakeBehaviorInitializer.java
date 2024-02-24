package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockYellowCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
