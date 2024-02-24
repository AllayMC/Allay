package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperDoorStackInitializer {
  static void init() {
    ItemTypes.COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemCopperDoorStack.class)
            .vanillaItem(VanillaItemId.COPPER_DOOR)
            .build();
  }
}
