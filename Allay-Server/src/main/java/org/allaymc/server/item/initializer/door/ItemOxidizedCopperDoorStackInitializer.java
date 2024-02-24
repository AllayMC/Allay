package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemOxidizedCopperDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperDoorStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_COPPER_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCopperDoorStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_COPPER_DOOR)
            .build();
  }
}
