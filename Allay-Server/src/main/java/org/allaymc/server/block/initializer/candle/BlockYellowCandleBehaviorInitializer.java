package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockYellowCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowCandleBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockYellowCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
