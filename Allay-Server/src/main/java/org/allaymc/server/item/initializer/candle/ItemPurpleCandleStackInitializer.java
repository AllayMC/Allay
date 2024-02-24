package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemPurpleCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCandleStackInitializer {
  static void init() {
    ItemTypes.PURPLE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCandleStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CANDLE)
            .build();
  }
}
