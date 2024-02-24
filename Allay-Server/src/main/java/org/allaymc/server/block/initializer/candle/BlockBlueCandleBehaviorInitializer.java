package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockBlueCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueCandleBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockBlueCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
