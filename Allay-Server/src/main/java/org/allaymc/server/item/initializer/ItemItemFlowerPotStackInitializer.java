package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemFlowerPotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFlowerPotStackInitializer {
  static void init() {
    ItemTypes.ITEM_FLOWER_POT_TYPE = ItemTypeBuilder
            .builder(ItemItemFlowerPotStack.class)
            .vanillaItem(VanillaItemId.ITEM_FLOWER_POT)
            .build();
  }
}
