package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemLimeCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCandleCakeStackInitializer {
  static void init() {
    ItemTypes.LIME_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemLimeCandleCakeStack.class)
            .vanillaItem(VanillaItemId.LIME_CANDLE_CAKE)
            .build();
  }
}
