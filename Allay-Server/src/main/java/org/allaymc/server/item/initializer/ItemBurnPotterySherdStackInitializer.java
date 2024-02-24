package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBurnPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBurnPotterySherdStackInitializer {
  static void init() {
    ItemTypes.BURN_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemBurnPotterySherdStack.class)
            .vanillaItem(VanillaItemId.BURN_POTTERY_SHERD)
            .build();
  }
}
