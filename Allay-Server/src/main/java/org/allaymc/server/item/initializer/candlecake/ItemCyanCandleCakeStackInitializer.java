package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemCyanCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanCandleCakeStackInitializer {
  static void init() {
    ItemTypes.CYAN_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemCyanCandleCakeStack.class)
            .vanillaItem(VanillaItemId.CYAN_CANDLE_CAKE)
            .build();
  }
}
