package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonRootsStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonRootsStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_ROOTS)
            .build();
  }
}
