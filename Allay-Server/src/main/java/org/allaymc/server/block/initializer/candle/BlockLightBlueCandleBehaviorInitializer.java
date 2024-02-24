package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockLightBlueCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCandleBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
