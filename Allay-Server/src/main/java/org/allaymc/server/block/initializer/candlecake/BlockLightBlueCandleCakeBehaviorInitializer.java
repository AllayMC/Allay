package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockLightBlueCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
