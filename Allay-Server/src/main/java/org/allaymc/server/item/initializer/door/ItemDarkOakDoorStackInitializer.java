package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemDarkOakDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakDoorStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakDoorStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_DOOR)
            .build();
  }
}
