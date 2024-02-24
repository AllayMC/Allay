package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemBlackCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCandleStackInitializer {
  static void init() {
    ItemTypes.BLACK_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBlackCandleStack.class)
            .vanillaItem(VanillaItemId.BLACK_CANDLE)
            .build();
  }
}
