package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemJungleDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleDoorStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemJungleDoorStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_DOOR)
            .build();
  }
}
