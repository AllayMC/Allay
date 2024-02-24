package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockLimeCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeCandleBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLimeCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
