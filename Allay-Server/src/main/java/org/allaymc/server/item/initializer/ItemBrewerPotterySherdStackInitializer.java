package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrewerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrewerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.BREWER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemBrewerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.BREWER_POTTERY_SHERD)
            .build();
  }
}
