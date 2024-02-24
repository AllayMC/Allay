package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockRedCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedCandleBehaviorInitializer {
  static void init() {
    BlockTypes.RED_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockRedCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
