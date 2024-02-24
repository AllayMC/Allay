package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHowlPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHowlPotterySherdStackInitializer {
  static void init() {
    ItemTypes.HOWL_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemHowlPotterySherdStack.class)
            .vanillaItem(VanillaItemId.HOWL_POTTERY_SHERD)
            .build();
  }
}
