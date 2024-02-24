package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement9Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement9StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_9_TYPE = ItemTypeBuilder
            .builder(ItemElement9Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_9)
            .build();
  }
}
