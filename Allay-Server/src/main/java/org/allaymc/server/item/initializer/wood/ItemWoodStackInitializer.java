package org.allaymc.server.item.initializer.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wood.ItemWoodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodStackInitializer {
  static void init() {
    ItemTypes.WOOD_TYPE = ItemTypeBuilder
            .builder(ItemWoodStack.class)
            .vanillaItem(VanillaItemId.WOOD)
            .build();
  }
}
