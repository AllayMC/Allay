package org.allaymc.server.item.initializer.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.door.ItemBambooDoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooDoorStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemBambooDoorStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_DOOR)
            .build();
  }
}
