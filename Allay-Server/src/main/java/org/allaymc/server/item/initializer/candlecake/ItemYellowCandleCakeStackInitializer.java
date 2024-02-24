package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemYellowCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCandleCakeStackInitializer {
  static void init() {
    ItemTypes.YELLOW_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemYellowCandleCakeStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CANDLE_CAKE)
            .build();
  }
}
