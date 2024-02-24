package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFlintStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlintStackInitializer {
  static void init() {
    ItemTypes.FLINT_TYPE = ItemTypeBuilder
            .builder(ItemFlintStack.class)
            .vanillaItem(VanillaItemId.FLINT)
            .build();
  }
}
