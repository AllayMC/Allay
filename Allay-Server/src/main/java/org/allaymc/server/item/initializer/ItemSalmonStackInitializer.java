package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSalmonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSalmonStackInitializer {
  static void init() {
    ItemTypes.SALMON_TYPE = ItemTypeBuilder
            .builder(ItemSalmonStack.class)
            .vanillaItem(VanillaItemId.SALMON)
            .build();
  }
}
