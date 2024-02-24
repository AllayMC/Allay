package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemDarkOakDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemDarkOakDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_DARK_OAK_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemDarkOakDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_DARK_OAK_DOOR)
            .build();
  }
}
