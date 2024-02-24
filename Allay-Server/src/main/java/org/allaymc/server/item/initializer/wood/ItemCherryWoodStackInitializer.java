package org.allaymc.server.item.initializer.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wood.ItemCherryWoodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryWoodStackInitializer {
  static void init() {
    ItemTypes.CHERRY_WOOD_TYPE = ItemTypeBuilder
            .builder(ItemCherryWoodStack.class)
            .vanillaItem(VanillaItemId.CHERRY_WOOD)
            .build();
  }
}
