package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemAcaciaDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaDoorStackInitializer {
  static void init() {
    ItemTypes.ACACIA_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaDoorStack.class)
            .vanillaItem(VanillaItemId.ACACIA_DOOR)
            .build();
  }
}
