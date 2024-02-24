package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockPurpleCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleCandleBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
