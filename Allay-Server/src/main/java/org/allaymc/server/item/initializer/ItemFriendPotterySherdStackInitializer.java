package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFriendPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFriendPotterySherdStackInitializer {
  static void init() {
    ItemTypes.FRIEND_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemFriendPotterySherdStack.class)
            .vanillaItem(VanillaItemId.FRIEND_POTTERY_SHERD)
            .build();
  }
}
