package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement57Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement57StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_57_TYPE = ItemTypeBuilder
            .builder(ItemElement57Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_57)
            .build();
  }
}
