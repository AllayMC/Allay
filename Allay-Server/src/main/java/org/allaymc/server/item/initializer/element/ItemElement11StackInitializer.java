package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement11Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement11StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_11_TYPE = ItemTypeBuilder
            .builder(ItemElement11Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_11)
            .build();
  }
}
