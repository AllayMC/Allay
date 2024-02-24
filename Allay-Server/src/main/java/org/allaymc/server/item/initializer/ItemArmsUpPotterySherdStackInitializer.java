package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemArmsUpPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmsUpPotterySherdStackInitializer {
  static void init() {
    ItemTypes.ARMS_UP_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemArmsUpPotterySherdStack.class)
            .vanillaItem(VanillaItemId.ARMS_UP_POTTERY_SHERD)
            .build();
  }
}
