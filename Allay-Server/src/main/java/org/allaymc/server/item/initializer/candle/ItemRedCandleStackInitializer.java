package org.allaymc.server.item.initializer.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.candle.ItemRedCandleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleStackInitializer {
  static void init() {
    ItemTypes.RED_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemRedCandleStack.class)
            .vanillaItem(VanillaItemId.RED_CANDLE)
            .build();
  }
}
