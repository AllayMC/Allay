package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemSoulCampfireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSoulCampfireStackInitializer {
  static void init() {
    ItemTypes.ITEM_SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemItemSoulCampfireStack.class)
            .vanillaItem(VanillaItemId.ITEM_SOUL_CAMPFIRE)
            .build();
  }
}
