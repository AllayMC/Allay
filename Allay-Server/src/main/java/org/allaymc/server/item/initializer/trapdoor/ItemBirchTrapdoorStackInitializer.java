package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemBirchTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchTrapdoorStackInitializer {
  static void init() {
    ItemTypes.BIRCH_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemBirchTrapdoorStack.class)
            .vanillaItem(VanillaItemId.BIRCH_TRAPDOOR)
            .build();
  }
}
