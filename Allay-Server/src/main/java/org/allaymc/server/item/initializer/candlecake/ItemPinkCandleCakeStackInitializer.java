package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemPinkCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleCakeStackInitializer {
  static void init() {
    ItemTypes.PINK_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemPinkCandleCakeStack.class)
            .vanillaItem(VanillaItemId.PINK_CANDLE_CAKE)
            .build();
  }
}
