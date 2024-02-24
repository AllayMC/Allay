package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemLightGrayCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCandleCakeStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCandleCakeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CANDLE_CAKE)
            .build();
  }
}
