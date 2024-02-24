package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockCyanCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanCandleBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockCyanCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
