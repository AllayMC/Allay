package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCandleStackInitializer {
  static void init() {
    ItemTypes.CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemCandleStack.class)
            .vanillaItem(VanillaItemId.CANDLE)
            .build();
  }
}
