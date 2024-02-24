package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemLightGrayCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCandleStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCandleStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CANDLE)
            .build();
  }
}
