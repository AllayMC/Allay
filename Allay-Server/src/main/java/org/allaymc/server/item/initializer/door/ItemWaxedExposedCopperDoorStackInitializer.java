package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWaxedExposedCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperDoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperDoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER_DOOR)
            .build();
  }
}
