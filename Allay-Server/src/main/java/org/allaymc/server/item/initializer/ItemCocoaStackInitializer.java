package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCocoaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCocoaStackInitializer {
  static void init() {
    ItemTypes.COCOA_TYPE = ItemTypeBuilder
            .builder(ItemCocoaStack.class)
            .vanillaItem(VanillaItemId.COCOA)
            .build();
  }
}
