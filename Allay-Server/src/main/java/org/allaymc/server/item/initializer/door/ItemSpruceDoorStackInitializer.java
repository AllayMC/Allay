package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemSpruceDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceDoorStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemSpruceDoorStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_DOOR)
            .build();
  }
}
