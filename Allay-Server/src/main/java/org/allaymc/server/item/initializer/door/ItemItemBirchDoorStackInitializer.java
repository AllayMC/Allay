package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemBirchDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBirchDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_BIRCH_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemBirchDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_BIRCH_DOOR)
            .build();
  }
}
