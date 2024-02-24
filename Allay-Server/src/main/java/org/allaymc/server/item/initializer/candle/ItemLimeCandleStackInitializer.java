package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemLimeCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCandleStackInitializer {
  static void init() {
    ItemTypes.LIME_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemLimeCandleStack.class)
            .vanillaItem(VanillaItemId.LIME_CANDLE)
            .build();
  }
}
