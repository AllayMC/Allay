package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemCrimsonDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCrimsonDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_CRIMSON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemCrimsonDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_CRIMSON_DOOR)
            .build();
  }
}
