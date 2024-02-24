package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCandleBehaviorInitializer {
  static void init() {
    BlockTypes.CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
