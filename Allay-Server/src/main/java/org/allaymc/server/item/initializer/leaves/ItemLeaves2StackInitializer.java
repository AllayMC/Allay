package org.allaymc.server.item.initializer.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leaves.ItemLeaves2Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeaves2StackInitializer {
  static void init() {
    ItemTypes.LEAVES2_TYPE = ItemTypeBuilder
            .builder(ItemLeaves2Stack.class)
            .vanillaItem(VanillaItemId.LEAVES2)
            .build();
  }
}
