package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemBlackCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCandleCakeStackInitializer {
  static void init() {
    ItemTypes.BLACK_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemBlackCandleCakeStack.class)
            .vanillaItem(VanillaItemId.BLACK_CANDLE_CAKE)
            .build();
  }
}
