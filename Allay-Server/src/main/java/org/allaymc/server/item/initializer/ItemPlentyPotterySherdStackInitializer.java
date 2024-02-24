package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPlentyPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPlentyPotterySherdStackInitializer {
  static void init() {
    ItemTypes.PLENTY_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemPlentyPotterySherdStack.class)
            .vanillaItem(VanillaItemId.PLENTY_POTTERY_SHERD)
            .build();
  }
}
