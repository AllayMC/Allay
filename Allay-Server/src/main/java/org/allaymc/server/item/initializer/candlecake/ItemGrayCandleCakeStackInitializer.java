package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemGrayCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCandleCakeStackInitializer {
  static void init() {
    ItemTypes.GRAY_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemGrayCandleCakeStack.class)
            .vanillaItem(VanillaItemId.GRAY_CANDLE_CAKE)
            .build();
  }
}
