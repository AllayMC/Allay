package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemCherryTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryTrapdoorStackInitializer {
  static void init() {
    ItemTypes.CHERRY_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemCherryTrapdoorStack.class)
            .vanillaItem(VanillaItemId.CHERRY_TRAPDOOR)
            .build();
  }
}
