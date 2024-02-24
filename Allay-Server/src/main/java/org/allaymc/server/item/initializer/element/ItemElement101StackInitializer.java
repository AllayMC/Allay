package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement101Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement101StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_101_TYPE = ItemTypeBuilder
            .builder(ItemElement101Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_101)
            .build();
  }
}
