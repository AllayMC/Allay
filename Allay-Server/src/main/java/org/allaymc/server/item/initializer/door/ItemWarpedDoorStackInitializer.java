package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWarpedDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedDoorStackInitializer {
  static void init() {
    ItemTypes.WARPED_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWarpedDoorStack.class)
            .vanillaItem(VanillaItemId.WARPED_DOOR)
            .build();
  }
}
