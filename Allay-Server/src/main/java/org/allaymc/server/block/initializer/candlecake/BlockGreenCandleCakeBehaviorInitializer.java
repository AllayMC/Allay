package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockGreenCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockGreenCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
