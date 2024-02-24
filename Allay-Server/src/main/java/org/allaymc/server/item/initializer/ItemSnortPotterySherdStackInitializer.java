package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSnortPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnortPotterySherdStackInitializer {
  static void init() {
    ItemTypes.SNORT_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemSnortPotterySherdStack.class)
            .vanillaItem(VanillaItemId.SNORT_POTTERY_SHERD)
            .build();
  }
}
