package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDangerPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDangerPotterySherdStackInitializer {
  static void init() {
    ItemTypes.DANGER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemDangerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.DANGER_POTTERY_SHERD)
            .build();
  }
}
