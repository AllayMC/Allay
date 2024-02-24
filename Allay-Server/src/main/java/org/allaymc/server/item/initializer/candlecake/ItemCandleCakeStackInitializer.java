package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCandleCakeStackInitializer {
  static void init() {
    ItemTypes.CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemCandleCakeStack.class)
            .vanillaItem(VanillaItemId.CANDLE_CAKE)
            .build();
  }
}
