package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemClayStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClayStackInitializer {
  static void init() {
    ItemTypes.CLAY_TYPE = ItemTypeBuilder
            .builder(ItemClayStack.class)
            .vanillaItem(VanillaItemId.CLAY)
            .build();
  }
}
