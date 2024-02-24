package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemExplorerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExplorerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.EXPLORER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemExplorerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.EXPLORER_POTTERY_SHERD)
            .build();
  }
}
