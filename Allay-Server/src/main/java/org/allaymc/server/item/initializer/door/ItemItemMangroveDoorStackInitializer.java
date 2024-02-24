package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemMangroveDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemMangroveDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_MANGROVE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemMangroveDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_MANGROVE_DOOR)
            .build();
  }
}
