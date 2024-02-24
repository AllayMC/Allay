package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement61Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement61StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_61_TYPE = ItemTypeBuilder
            .builder(ItemElement61Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_61)
            .build();
  }
}
