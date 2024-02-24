package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemVineStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVineStackInitializer {
  static void init() {
    ItemTypes.VINE_TYPE = ItemTypeBuilder
            .builder(ItemVineStack.class)
            .vanillaItem(VanillaItemId.VINE)
            .build();
  }
}
