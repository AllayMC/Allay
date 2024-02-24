package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDioriteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDioriteStackInitializer {
  static void init() {
    ItemTypes.DIORITE_TYPE = ItemTypeBuilder
            .builder(ItemDioriteStack.class)
            .vanillaItem(VanillaItemId.DIORITE)
            .build();
  }
}
