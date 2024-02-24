package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAnglerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAnglerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.ANGLER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemAnglerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.ANGLER_POTTERY_SHERD)
            .build();
  }
}
