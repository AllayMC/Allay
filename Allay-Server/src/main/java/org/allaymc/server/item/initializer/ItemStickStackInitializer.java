package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickStackInitializer {
  static void init() {
    ItemTypes.STICK_TYPE = ItemTypeBuilder
            .builder(ItemStickStack.class)
            .vanillaItem(VanillaItemId.STICK)
            .build();
  }
}
