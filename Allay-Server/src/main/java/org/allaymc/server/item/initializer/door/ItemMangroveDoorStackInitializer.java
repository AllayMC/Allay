package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemMangroveDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveDoorStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemMangroveDoorStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_DOOR)
            .build();
  }
}
