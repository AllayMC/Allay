package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemIronTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronTrapdoorStackInitializer {
  static void init() {
    ItemTypes.IRON_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemIronTrapdoorStack.class)
            .vanillaItem(VanillaItemId.IRON_TRAPDOOR)
            .build();
  }
}
