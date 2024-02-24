package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockOrangeCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeCandleBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
