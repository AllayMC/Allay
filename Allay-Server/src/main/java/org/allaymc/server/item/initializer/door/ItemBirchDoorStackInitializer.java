package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemBirchDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchDoorStackInitializer {
  static void init() {
    ItemTypes.BIRCH_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemBirchDoorStack.class)
            .vanillaItem(VanillaItemId.BIRCH_DOOR)
            .build();
  }
}
