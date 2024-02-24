package org.allaymc.server.block.initializer.candlecake;

import org.allaymc.api.block.interfaces.candlecake.BlockPurpleCandleCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleCandleCakeBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
  }
}
