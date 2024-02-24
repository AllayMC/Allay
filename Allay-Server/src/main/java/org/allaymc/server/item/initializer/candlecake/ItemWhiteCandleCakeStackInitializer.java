package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemWhiteCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCandleCakeStackInitializer {
  static void init() {
    ItemTypes.WHITE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCandleCakeStack.class)
            .vanillaItem(VanillaItemId.WHITE_CANDLE_CAKE)
            .build();
  }
}
