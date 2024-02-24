package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemCyanCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanCandleStackInitializer {
  static void init() {
    ItemTypes.CYAN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemCyanCandleStack.class)
            .vanillaItem(VanillaItemId.CYAN_CANDLE)
            .build();
  }
}
