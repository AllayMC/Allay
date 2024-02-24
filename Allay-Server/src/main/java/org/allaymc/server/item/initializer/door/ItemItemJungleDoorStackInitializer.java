package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemJungleDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemJungleDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_JUNGLE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemJungleDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_JUNGLE_DOOR)
            .build();
  }
}
