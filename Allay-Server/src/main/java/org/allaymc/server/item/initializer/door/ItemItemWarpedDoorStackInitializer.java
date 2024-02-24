package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemWarpedDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWarpedDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_WARPED_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemWarpedDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_WARPED_DOOR)
            .build();
  }
}
