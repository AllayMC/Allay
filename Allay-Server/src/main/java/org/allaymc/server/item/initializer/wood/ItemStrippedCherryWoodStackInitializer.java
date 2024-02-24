package org.allaymc.server.item.initializer.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wood.ItemStrippedCherryWoodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCherryWoodStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_CHERRY_WOOD_TYPE = ItemTypeBuilder
            .builder(ItemStrippedCherryWoodStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_CHERRY_WOOD)
            .build();
  }
}
