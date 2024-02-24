package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemWaxedOxidizedCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperDoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCopperDoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER_DOOR)
            .build();
  }
}
