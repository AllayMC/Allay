package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemLightBlueCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCandleCakeStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCandleCakeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CANDLE_CAKE)
            .build();
  }
}
