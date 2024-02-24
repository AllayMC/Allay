package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemLightBlueCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCandleStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCandleStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CANDLE)
            .build();
  }
}
