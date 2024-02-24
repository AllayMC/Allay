package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement118Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement118StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_118_TYPE = ItemTypeBuilder
            .builder(ItemElement118Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_118)
            .build();
  }
}
