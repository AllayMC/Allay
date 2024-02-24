package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockWhiteCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteCandleBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
