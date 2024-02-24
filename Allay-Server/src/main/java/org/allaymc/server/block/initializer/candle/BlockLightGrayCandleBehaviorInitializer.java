package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockLightGrayCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayCandleBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
