package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemWhiteCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCandleStackInitializer {
  static void init() {
    ItemTypes.WHITE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCandleStack.class)
            .vanillaItem(VanillaItemId.WHITE_CANDLE)
            .build();
  }
}
