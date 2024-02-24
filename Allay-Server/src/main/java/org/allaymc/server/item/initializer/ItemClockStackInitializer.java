package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemClockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClockStackInitializer {
  static void init() {
    ItemTypes.CLOCK_TYPE = ItemTypeBuilder
            .builder(ItemClockStack.class)
            .vanillaItem(VanillaItemId.CLOCK)
            .build();
  }
}
