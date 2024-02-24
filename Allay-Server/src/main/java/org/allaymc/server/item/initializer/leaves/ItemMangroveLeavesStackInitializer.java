package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemMangroveLeavesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveLeavesStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemMangroveLeavesStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_LEAVES)
            .build();
  }
}
