package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemIronDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronDoorStackInitializer {
  static void init() {
    ItemTypes.IRON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemIronDoorStack.class)
            .vanillaItem(VanillaItemId.IRON_DOOR)
            .build();
  }
}
