package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMangroveRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveRootsStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemMangroveRootsStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_ROOTS)
            .build();
  }
}
