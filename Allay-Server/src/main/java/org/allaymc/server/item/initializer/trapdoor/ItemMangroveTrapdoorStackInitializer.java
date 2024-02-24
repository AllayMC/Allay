package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemMangroveTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveTrapdoorStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemMangroveTrapdoorStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_TRAPDOOR)
            .build();
  }
}
