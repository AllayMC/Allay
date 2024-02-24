package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemWoodenDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWoodenDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_WOODEN_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemWoodenDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_WOODEN_DOOR)
            .build();
  }
}
