package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemCherryLeavesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryLeavesStackInitializer {
  static void init() {
    ItemTypes.CHERRY_LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemCherryLeavesStack.class)
            .vanillaItem(VanillaItemId.CHERRY_LEAVES)
            .build();
  }
}
