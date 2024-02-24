package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLoomStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLoomStackInitializer {
  static void init() {
    ItemTypes.LOOM_TYPE = ItemTypeBuilder
            .builder(ItemLoomStack.class)
            .vanillaItem(VanillaItemId.LOOM)
            .build();
  }
}
