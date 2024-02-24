package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemLeavesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeavesStackInitializer {
  static void init() {
    ItemTypes.LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemLeavesStack.class)
            .vanillaItem(VanillaItemId.LEAVES)
            .build();
  }
}
