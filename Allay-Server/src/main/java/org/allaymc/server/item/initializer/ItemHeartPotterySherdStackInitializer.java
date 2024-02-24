package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHeartPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeartPotterySherdStackInitializer {
  static void init() {
    ItemTypes.HEART_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemHeartPotterySherdStack.class)
            .vanillaItem(VanillaItemId.HEART_POTTERY_SHERD)
            .build();
  }
}
