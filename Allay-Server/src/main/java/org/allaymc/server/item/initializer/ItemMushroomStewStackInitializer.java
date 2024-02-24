package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMushroomStewStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMushroomStewStackInitializer {
  static void init() {
    ItemTypes.MUSHROOM_STEW_TYPE = ItemTypeBuilder
            .builder(ItemMushroomStewStack.class)
            .vanillaItem(VanillaItemId.MUSHROOM_STEW)
            .build();
  }
}
