package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPrizePotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrizePotterySherdStackInitializer {
  static void init() {
    ItemTypes.PRIZE_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemPrizePotterySherdStack.class)
            .vanillaItem(VanillaItemId.PRIZE_POTTERY_SHERD)
            .build();
  }
}
