package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonHyphaeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonHyphaeStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonHyphaeStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_HYPHAE)
            .build();
  }
}
