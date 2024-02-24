package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement98Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement98StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_98_TYPE = ItemTypeBuilder
            .builder(ItemElement98Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_98)
            .build();
  }
}
