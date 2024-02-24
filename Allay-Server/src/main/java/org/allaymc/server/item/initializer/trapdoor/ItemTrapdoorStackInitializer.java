package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrapdoorStackInitializer {
  static void init() {
    ItemTypes.TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemTrapdoorStack.class)
            .vanillaItem(VanillaItemId.TRAPDOOR)
            .build();
  }
}
