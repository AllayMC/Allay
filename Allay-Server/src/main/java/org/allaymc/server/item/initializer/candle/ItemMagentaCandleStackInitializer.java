package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemMagentaCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCandleStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCandleStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CANDLE)
            .build();
  }
}
