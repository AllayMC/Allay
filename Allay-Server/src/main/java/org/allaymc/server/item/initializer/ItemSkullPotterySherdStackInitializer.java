package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSkullPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullPotterySherdStackInitializer {
  static void init() {
    ItemTypes.SKULL_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemSkullPotterySherdStack.class)
            .vanillaItem(VanillaItemId.SKULL_POTTERY_SHERD)
            .build();
  }
}
