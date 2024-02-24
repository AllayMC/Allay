package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemOrangeCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCandleStackInitializer {
  static void init() {
    ItemTypes.ORANGE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeCandleStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CANDLE)
            .build();
  }
}
