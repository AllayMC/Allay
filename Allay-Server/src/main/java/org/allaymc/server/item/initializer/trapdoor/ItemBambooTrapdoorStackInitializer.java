package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemBambooTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooTrapdoorStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemBambooTrapdoorStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_TRAPDOOR)
            .build();
  }
}
