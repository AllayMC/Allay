package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCampfireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCampfireStackInitializer {
  static void init() {
    ItemTypes.CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemCampfireStack.class)
            .vanillaItem(VanillaItemId.CAMPFIRE)
            .build();
  }
}
