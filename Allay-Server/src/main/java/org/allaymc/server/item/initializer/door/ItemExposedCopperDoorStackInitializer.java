package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemExposedCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperDoorStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperDoorStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER_DOOR)
            .build();
  }
}
