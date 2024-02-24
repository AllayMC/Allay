package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCocoaBeansStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCocoaBeansStackInitializer {
  static void init() {
    ItemTypes.COCOA_BEANS_TYPE = ItemTypeBuilder
            .builder(ItemCocoaBeansStack.class)
            .vanillaItem(VanillaItemId.COCOA_BEANS)
            .build();
  }
}
