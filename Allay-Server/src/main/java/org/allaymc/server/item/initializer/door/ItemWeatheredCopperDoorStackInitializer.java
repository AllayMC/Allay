package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWeatheredCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperDoorStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperDoorStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER_DOOR)
            .build();
  }
}
