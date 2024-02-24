package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWaxedCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperDoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperDoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER_DOOR)
            .build();
  }
}
