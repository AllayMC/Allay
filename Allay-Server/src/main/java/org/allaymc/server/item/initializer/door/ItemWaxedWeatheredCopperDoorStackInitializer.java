package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWaxedWeatheredCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperDoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperDoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_DOOR)
            .build();
  }
}
