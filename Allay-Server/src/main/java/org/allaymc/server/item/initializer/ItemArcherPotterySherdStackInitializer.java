package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemArcherPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArcherPotterySherdStackInitializer {
  static void init() {
    ItemTypes.ARCHER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemArcherPotterySherdStack.class)
            .vanillaItem(VanillaItemId.ARCHER_POTTERY_SHERD)
            .build();
  }
}
