package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemPinkCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleStackInitializer {
  static void init() {
    ItemTypes.PINK_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemPinkCandleStack.class)
            .vanillaItem(VanillaItemId.PINK_CANDLE)
            .build();
  }
}
