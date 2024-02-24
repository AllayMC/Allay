package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMournerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMournerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.MOURNER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemMournerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.MOURNER_POTTERY_SHERD)
            .build();
  }
}
