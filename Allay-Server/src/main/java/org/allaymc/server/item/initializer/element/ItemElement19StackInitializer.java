package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement19Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement19StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_19_TYPE = ItemTypeBuilder
            .builder(ItemElement19Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_19)
            .build();
  }
}
