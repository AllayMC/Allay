package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHeartbreakPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeartbreakPotterySherdStackInitializer {
  static void init() {
    ItemTypes.HEARTBREAK_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemHeartbreakPotterySherdStack.class)
            .vanillaItem(VanillaItemId.HEARTBREAK_POTTERY_SHERD)
            .build();
  }
}
