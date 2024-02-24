package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSkullStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullStackInitializer {
  static void init() {
    ItemTypes.SKULL_TYPE = ItemTypeBuilder
            .builder(ItemSkullStack.class)
            .vanillaItem(VanillaItemId.SKULL)
            .build();
  }
}
