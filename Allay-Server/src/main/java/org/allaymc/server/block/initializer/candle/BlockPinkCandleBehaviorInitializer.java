package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockPinkCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkCandleBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
