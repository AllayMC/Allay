package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemGreenCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCandleStackInitializer {
  static void init() {
    ItemTypes.GREEN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemGreenCandleStack.class)
            .vanillaItem(VanillaItemId.GREEN_CANDLE)
            .build();
  }
}
