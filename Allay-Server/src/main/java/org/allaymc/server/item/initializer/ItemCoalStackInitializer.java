package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCoalStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalStackInitializer {
  static void init() {
    ItemTypes.COAL_TYPE = ItemTypeBuilder
            .builder(ItemCoalStack.class)
            .vanillaItem(VanillaItemId.COAL)
            .build();
  }
}
