package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockGreenCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenCandleBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockGreenCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
