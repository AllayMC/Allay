package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemPurpleCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCandleCakeStackInitializer {
  static void init() {
    ItemTypes.PURPLE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCandleCakeStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CANDLE_CAKE)
            .build();
  }
}
