package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemItemAcaciaDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemAcaciaDoorStackInitializer {
  static void init() {
    ItemTypes.ITEM_ACACIA_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemAcaciaDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_ACACIA_DOOR)
            .build();
  }
}
