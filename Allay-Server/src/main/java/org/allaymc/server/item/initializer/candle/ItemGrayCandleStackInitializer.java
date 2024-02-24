package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemGrayCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCandleStackInitializer {
  static void init() {
    ItemTypes.GRAY_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemGrayCandleStack.class)
            .vanillaItem(VanillaItemId.GRAY_CANDLE)
            .build();
  }
}
