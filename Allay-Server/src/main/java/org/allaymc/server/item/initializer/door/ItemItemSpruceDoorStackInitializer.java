package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemSpruceDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSpruceDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_SPRUCE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemSpruceDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_SPRUCE_DOOR)
            .build();
  }
}
