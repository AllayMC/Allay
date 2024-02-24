package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement13Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement13StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_13_TYPE = ItemTypeBuilder
            .builder(ItemElement13Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_13)
            .build();
  }
}
