package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShelterPotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShelterPotterySherdStackInitializer {
  static void init() {
    ItemTypes.SHELTER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemShelterPotterySherdStack.class)
            .vanillaItem(VanillaItemId.SHELTER_POTTERY_SHERD)
            .build();
  }
}
