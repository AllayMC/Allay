package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockMagentaCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockMagentaCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
