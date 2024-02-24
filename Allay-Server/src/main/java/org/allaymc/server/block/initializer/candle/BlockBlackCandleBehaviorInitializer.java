package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockBlackCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackCandleBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBlackCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
