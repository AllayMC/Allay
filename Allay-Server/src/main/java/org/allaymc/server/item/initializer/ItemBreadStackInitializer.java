package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBreadStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBreadStackInitializer {
  static void init() {
    ItemTypes.BREAD_TYPE = ItemTypeBuilder
            .builder(ItemBreadStack.class)
            .vanillaItem(VanillaItemId.BREAD)
            .build();
  }
}
