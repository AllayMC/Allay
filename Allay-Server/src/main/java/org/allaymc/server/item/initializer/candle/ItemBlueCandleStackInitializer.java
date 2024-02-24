package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemBlueCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCandleStackInitializer {
  static void init() {
    ItemTypes.BLUE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBlueCandleStack.class)
            .vanillaItem(VanillaItemId.BLUE_CANDLE)
            .build();
  }
}
