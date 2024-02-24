package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemIronDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemIronDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_IRON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemIronDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_IRON_DOOR)
            .build();
  }
}
