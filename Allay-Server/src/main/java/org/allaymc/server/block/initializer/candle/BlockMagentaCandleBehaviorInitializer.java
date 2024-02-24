package org.allaymc.server.block.initializer.candle;

import org.allaymc.api.block.interfaces.candle.BlockMagentaCandleBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaCandleBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_CANDLE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE)
            .setProperties(VanillaBlockPropertyTypes.CANDLES, VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
