package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWoodenDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenDoorStackInitializer {
  static void init() {
    ItemTypes.WOODEN_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWoodenDoorStack.class)
            .vanillaItem(VanillaItemId.WOODEN_DOOR)
            .build();
  }
}
