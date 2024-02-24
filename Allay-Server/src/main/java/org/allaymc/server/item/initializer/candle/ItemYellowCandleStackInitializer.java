package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemYellowCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCandleStackInitializer {
  static void init() {
    ItemTypes.YELLOW_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemYellowCandleStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CANDLE)
            .build();
  }
}
