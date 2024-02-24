package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemOrangeCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCandleCakeStackInitializer {
  static void init() {
    ItemTypes.ORANGE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeCandleCakeStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CANDLE_CAKE)
            .build();
  }
}
