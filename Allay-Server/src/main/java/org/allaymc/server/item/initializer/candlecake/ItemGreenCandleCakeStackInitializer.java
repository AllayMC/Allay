package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemGreenCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCandleCakeStackInitializer {
  static void init() {
    ItemTypes.GREEN_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemGreenCandleCakeStack.class)
            .vanillaItem(VanillaItemId.GREEN_CANDLE_CAKE)
            .build();
  }
}
