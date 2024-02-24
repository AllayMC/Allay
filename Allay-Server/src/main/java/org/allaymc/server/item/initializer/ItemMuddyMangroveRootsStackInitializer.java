package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMuddyMangroveRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMuddyMangroveRootsStackInitializer {
  static void init() {
    ItemTypes.MUDDY_MANGROVE_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemMuddyMangroveRootsStack.class)
            .vanillaItem(VanillaItemId.MUDDY_MANGROVE_ROOTS)
            .build();
  }
}
