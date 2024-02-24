package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockBrownCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownCandleBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBrownCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
