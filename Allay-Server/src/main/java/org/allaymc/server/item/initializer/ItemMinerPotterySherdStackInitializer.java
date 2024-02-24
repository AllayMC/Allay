package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMinerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMinerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.MINER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemMinerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.MINER_POTTERY_SHERD)
            .build();
  }
}
