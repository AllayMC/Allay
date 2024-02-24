package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemCherryDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryDoorStackInitializer {
  static void init() {
    ItemTypes.CHERRY_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemCherryDoorStack.class)
            .vanillaItem(VanillaItemId.CHERRY_DOOR)
            .build();
  }
}
