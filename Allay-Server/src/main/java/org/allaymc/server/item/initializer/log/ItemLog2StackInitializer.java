package org.allaymc.server.item.initializer.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.log.ItemLog2Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLog2StackInitializer {
  static void init() {
    ItemTypes.LOG2_TYPE = ItemTypeBuilder
            .builder(ItemLog2Stack.class)
            .vanillaItem(VanillaItemId.LOG2)
            .build();
  }
}
