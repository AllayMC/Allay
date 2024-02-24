package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulCampfireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulCampfireStackInitializer {
  static void init() {
    ItemTypes.SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemSoulCampfireStack.class)
            .vanillaItem(VanillaItemId.SOUL_CAMPFIRE)
            .build();
  }
}
