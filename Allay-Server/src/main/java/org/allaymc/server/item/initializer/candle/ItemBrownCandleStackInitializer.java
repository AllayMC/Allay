package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemBrownCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCandleStackInitializer {
  static void init() {
    ItemTypes.BROWN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBrownCandleStack.class)
            .vanillaItem(VanillaItemId.BROWN_CANDLE)
            .build();
  }
}
