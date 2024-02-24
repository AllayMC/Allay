package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemRedCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleCakeStackInitializer {
  static void init() {
    ItemTypes.RED_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemRedCandleCakeStack.class)
            .vanillaItem(VanillaItemId.RED_CANDLE_CAKE)
            .build();
  }
}
