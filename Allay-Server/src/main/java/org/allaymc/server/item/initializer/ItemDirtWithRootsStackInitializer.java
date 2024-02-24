package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDirtWithRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDirtWithRootsStackInitializer {
  static void init() {
    ItemTypes.DIRT_WITH_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemDirtWithRootsStack.class)
            .vanillaItem(VanillaItemId.DIRT_WITH_ROOTS)
            .build();
  }
}
