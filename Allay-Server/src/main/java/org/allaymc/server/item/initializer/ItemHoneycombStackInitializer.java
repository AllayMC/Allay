package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHoneycombStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoneycombStackInitializer {
  static void init() {
    ItemTypes.HONEYCOMB_TYPE = ItemTypeBuilder
            .builder(ItemHoneycombStack.class)
            .vanillaItem(VanillaItemId.HONEYCOMB)
            .build();
  }
}
