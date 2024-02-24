package org.allaymc.server.item.initializer.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candlecake.ItemBrownCandleCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCandleCakeStackInitializer {
  static void init() {
    ItemTypes.BROWN_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemBrownCandleCakeStack.class)
            .vanillaItem(VanillaItemId.BROWN_CANDLE_CAKE)
            .build();
  }
}
