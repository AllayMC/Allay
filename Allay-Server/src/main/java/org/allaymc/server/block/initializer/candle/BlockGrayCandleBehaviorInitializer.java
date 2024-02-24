package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockGrayCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayCandleBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockGrayCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
