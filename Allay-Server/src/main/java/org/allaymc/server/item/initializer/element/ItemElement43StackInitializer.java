package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement43Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement43StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_43_TYPE = ItemTypeBuilder
            .builder(ItemElement43Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_43)
            .build();
  }
}
