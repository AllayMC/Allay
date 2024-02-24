package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemCampfireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCampfireStackInitializer {
  static void init() {
    ItemTypes.ITEM_CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemItemCampfireStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAMPFIRE)
            .build();
  }
}
