package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemMagentaCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCandleCakeStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCandleCakeStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CANDLE_CAKE)
            .build();
  }
}
