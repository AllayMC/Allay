package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemBlueCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCandleCakeStackInitializer {
  static void init() {
    ItemTypes.BLUE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemBlueCandleCakeStack.class)
            .vanillaItem(VanillaItemId.BLUE_CANDLE_CAKE)
            .build();
  }
}
